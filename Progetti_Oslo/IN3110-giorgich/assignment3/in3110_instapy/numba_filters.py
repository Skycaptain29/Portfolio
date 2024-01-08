"""numba-optimized filters"""
from __future__ import annotations

import numpy as np
from numba import jit

@jit(nopython=True)
def numba_color2gray(image: np.array) -> np.array:
    """Convert rgb pixel array to grayscale using a weighted sum of the RGB values of the pixel
    done by using the numba library

    Args:
        image (np.array)
    Returns:
        np.array: gray_image
    """
    gray_image = np.empty_like(image)
    # iterate through the image, and apply the grayscale transform

    # get the shape of the picture
    height, width, depth = gray_image.shape

    # iterate trough the image, and for every pixel calculate the weighted sum and apply it to the RGB values
    for i in range(height):
        for j in range(width):
            w_sum = image[i][j][0] * 0.21 + image[i][j][1] * 0.72 + image[i][j][2] * 0.07
            gray_image[i][j][0] = w_sum
            gray_image[i][j][1] = w_sum
            gray_image[i][j][2] = w_sum

    # transform the array to type uint8
    gray_image = gray_image.astype("uint8")
    return gray_image

@jit(nopython=True)
def numba_color2sepia(image: np.array) -> np.array:
    """Convert rgb pixel array to sepia by using a sepia transformation matrix. The matrix is used row by
    to calculate the respective RGB values based on the weights

    Args:
        image (np.array)
    Returns:
        np.array: sepia_image
    """
    sepia_image = np.empty_like(image)
    # Iterate through the pixels
    # applying the sepia matrix

    # get the shape of the picture
    height, width, depth = sepia_image.shape

    # define the sepia weights matrix
    weights = [
        [ 0.393, 0.769, 0.189],
        [ 0.349, 0.686, 0.168],
        [ 0.272, 0.534, 0.131],
    ]

    # iterate trough the image, and for every pixel calculate the weighted sum using the corresponding row of the weight matrix
    # and apply it to the corresponding val of the image
    for i in range(height):
        for j in range(width):
            sepiaR = image[i][j][0] * weights[0][0] + image[i][j][1] * weights[0][1] + image[i][j][2] * weights[0][2]
            sepiaG = image[i][j][0] * weights[1][0] + image[i][j][1] * weights[1][1] + image[i][j][2] * weights[1][2]
            sepiaB = image[i][j][0] * weights[2][0] + image[i][j][1] * weights[2][1] + image[i][j][2] * weights[2][2]

            # use min to truncate the values to a max of 255
            sepia_image[i][j][0] = min(255,sepiaR)
            sepia_image[i][j][1] = min(255,sepiaG)
            sepia_image[i][j][2] = min(255,sepiaB)

    # transform the array to type uint8
    sepia_image = sepia_image.astype("uint8")
    return sepia_image