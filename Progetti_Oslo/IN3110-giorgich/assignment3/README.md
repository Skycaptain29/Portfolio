Name: INSTAPY

The instapy package offers two filters to modify images. The first filter is the grayscale filter, which turns the given image to grayscale. The second filter is the sepia filter, which applies a sepia effect to the given image

To install the package run 
    ```
    sudo pip install .
    ```
in the folder where the pyproject.toml file is.

To run the package either use
    ```
    python3 -m in3110_instapy <arguments>
    ```
or
    ```
    instapy <arguments>
    ```
to see the complete list of argument use
    ```
    python3 -m in3110_instapy -h
    ```
or
    ```
    instapy -h
    ```

Example use of the instapy module

    ```
    instapy rain.jpg -i numpy -g
    ```
    generates a grayscale image with the grayscale filter using the default python implementation and shows it without saving it

    ```
    instapy rain.jpg -i numba -s -sg 0.5 -o sepia_image
    ```
    generates a sepia image with intensity of 0.5 using the numba implementation, and saves it as a file named sepia_image.jpg

