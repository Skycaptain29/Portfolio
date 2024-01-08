"""numpy implementation of image filters"""
from __future__ import annotations

import numpy as np


def numpy_color2gray(image: np.array) -> np.array:
    """Convert rgb pixel array to grayscale
       using an array of weights calculate for each pixel the correspondant grayscale value done by using
       numpy slicing

    Args:
        image (np.array)
    Returns:
        np.array: gray_image
    """

    gray_image = np.empty_like(image)

    # define a weights array
    weights = [0.21, 0.72, 0.07]
    # calculate the weighted sum for the entire image
    w_sum = np.sum(image * weights, axis=2)
    # apply the weighted sum to the RGB values of the entire image
    gray_image[:, :, 0] = w_sum
    gray_image[:, :, 1] = w_sum
    gray_image[:, :, 2] = w_sum

    # transform the array to type uint8
    gray_image = gray_image.astype("uint8")
    return gray_image


def numpy_color2sepia(image: np.array, k: float = 1) -> np.array:
    """Convert rgb pixel array to sepia by using a sepia transformation matrix. The matrix is used row by
    to calculate the respective RGB values based on the weights, and the parameter k is used to determine the
    intensity of the filter, with 0 the lowest intensity and 1 the highest intensity



    Args:
        image (np.array)
        k (float): amount of sepia (optional)

    Returns:
        np.array: sepia_image
    """

    if not 0 <= k <= 1:
        # validate k (optional)
        raise ValueError(f"k must be between [0-1], got {k=}")


    # calculate the weights based on the value of k: if k = 0 the weights are a identity matrix, if k = 1
    # the weights are the standard values for the sepia filter
    weights = [
        [ 0.393 + 0.607 * (1 - k), 0.769 - 0.769 * (1 - k), 0.189 - 0.189 * (1 - k)],
        [ 0.349 - 0.349 * (1 - k), 0.686 + 0.314 * (1 - k), 0.168 - 0.168 * (1 - k)],
        [ 0.272 - 0.272 * (1 - k), 0.534 - 0.534 * (1 - k), 0.131 + 0.869 * (1 - k)]
    ]

    sepia_image = np.empty_like(image)

    # Calculate the weighted sums for the RGB of the whole image channels using the weights
    sepiaR = image[:, :, 0] * weights[0][0] + image[:, :, 1] * weights[0][1] + image[:, :, 2] * weights[0][2]
    sepiaG = image[:, :, 0] * weights[1][0] + image[:, :, 1] * weights[1][1] + image[:, :, 2] * weights[1][2]
    sepiaB = image[:, :, 0] * weights[2][0] + image[:, :, 1] * weights[2][1] + image[:, :, 2] * weights[2][2]

    # Truncat the values in the tree weighted sums so that it is not greater than 255 and it is not less than 0
    sepia_image[:, :, 0] = np.maximum(0, np.minimum(255, sepiaR))
    sepia_image[:, :, 1] = np.maximum(0, np.minimum(255, sepiaG))
    sepia_image[:, :, 2] = np.maximum(0, np.minimum(255, sepiaB))

    # transform the array to type uint8
    sepia_image = sepia_image.astype("uint8")
    return sepia_image
