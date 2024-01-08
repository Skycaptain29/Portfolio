"""Command-line (script) interface to instapy"""
from __future__ import annotations

import argparse
import sys

import in3110_instapy
import numpy as np
from PIL import Image
from in3110_instapy.timing import time_one

from . import io, get_filter


def run_filter(
    file: str,
    out_file: str = None,
    implementation: str = "python",
    filter: str = "color2gray",
    scale: int = 1,
    runtime: bool = False,
    sepia_grade: float = 1.0,
) -> None:
    """Run the selected filter
    the filter is selected with the function get_filter
    
    Args:
        file (str): the path to the image file to apply the filter to
        out_file (str, optional): the path where the image is wished to be saved to
        implementation (str, optional): the name of the implementation to be use
        filter (str, optional): the name of the filter to be used
        scale (int, optional): the value by wich the image is to be scaled
        runtime (bool, optional): flag to decide if the diagnostics have to be printed
        sepia_grade (float, optional): the intensity for the numpy implementation of the sepia filter

    Returns:
        None
    """
    
    # load the image from a file
    image = Image.open(file)
    if scale != 1:
        # Resize image, if needed
        image = image.resize((image.width * scale, image.height * scale))
    image =  np.asarray(image)

    # Get the filter function
    filter_function = get_filter(filter, implementation)
    # Apply the filter
    # if the runtime flag is true run the computation 3 times and time it
    if runtime:
        time = time_one(filter_function, image, calls=3)
        print(f"Average time of {filter} with {implementation} over 3 runs: {time}s")
    else:
        # if the implementation is numpy and filter color2sepia pass the sepia_grade value to the function
        if(implementation == "numpy" and filter == "color2sepia"):
            filtered = filter_function(image, sepia_grade)
        else:
            filtered = filter_function(image)
        if out_file:
            # save the file
            if not ".jpg" in out_file:
                io.write_image(filtered, f"{out_file}.jpg")
            else:
                out = out_file.split(".")
                io.write_image(filtered, f"{out[0]}.jpg")
            
        else:
            # not asked to save, display it instead
            io.display(filtered)


def main(argv=None):
    """Parse the command-line and call run_filter with the arguments"""
    if argv is None:
        argv = sys.argv[1:]

    parser = argparse.ArgumentParser(description="Applies filters to images")

    # filename is positional and required
    parser.add_argument("file", help="The filename to apply filter to", type=str) 
    parser.add_argument("-o", "--out", help="The output filename, without extension. If none is given the image will only be displayed", type=str, default=False, const=False, nargs="?")

    # Add required arguments

    parser.add_argument("-g", "--gray", help="Select the grayscale filter. On by default", action="store_true", default=True)
    parser.add_argument("-s", "--sepia", help="Select the sepia filter. Off by default", action="store_true", default=False)
    parser.add_argument("-sg", "--sepiaGrade", help="The intensity of the sepia filter. Must be a number between 0 and 1. By default the value is 1", const=1, default=1, nargs="?", type=float)
    parser.add_argument("-sc", "--scale", help="The scale factor to appy to the input image", const=1, type=float, default=1, nargs="?")
    parser.add_argument("-i", "--implementation", help="Select the implementation for the filter between {python, numpy, numba}. Python implementation is selected by default", choices=['python', 'numpy', 'numba'], const="python", nargs="?", type=str, default="python")
    parser.add_argument("-r", "--runtime", help="Tracks the average runtime of the selected filter over 3 runs. Off by default", action="store_true", default=False)
    

    # parse arguments and call run_filter
    args = parser.parse_args()
    run_filter(file=args.file, out_file=args.out, implementation=args.implementation, scale=int(args.scale), filter=("color2sepia" if args.sepia else "color2gray"), runtime=args.runtime, sepia_grade=args.sepiaGrade)