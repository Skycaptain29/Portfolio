import numpy.testing as nt
from in3110_instapy.numba_filters import numba_color2gray, numba_color2sepia
import random
import numpy as np

def test_color2gray(image, reference_gray):
    # run color2gray
    grayimage = numba_color2gray(image)
    # check that the result has the right shape, type
    assert grayimage.shape == image.shape
    assert isinstance(grayimage, np.ndarray)
    assert grayimage.dtype == np.uint8
    # assert uniform r,g,b values
    # select 10 random pixels
    x = []
    y = []
    for n in range(10):
        x.append(random.randrange(grayimage.shape[0]))
        y.append(random.randrange(grayimage.shape[1]))
    # for each of the selected pixels assert if the RGB values are what they should be
    for x,y in zip(x,y):
        grayVal = np.uint8(image[x,y,0] * 0.21 + image[x,y,1] * 0.72 + image[x,y,2] * 0.07)
        assert grayimage[x,y,0] == grayVal and grayimage[x,y,1] == grayVal and grayimage[x,y,2] == grayVal
    
    # check if the returned image of numpy_color2gray is equal to the reference gray image
    assert np.allclose(grayimage, reference_gray)


def test_color2sepia(image, reference_sepia):
    # run color2sepia
    sepiaimage = numba_color2sepia(image)
    # check that the result has the right shape, type
    assert sepiaimage.shape == image.shape
    assert isinstance(sepiaimage, np.ndarray)
    assert sepiaimage.dtype == np.uint8
    # verify some individual pixel samples
    # according to the sepia matrix
    # select 10 random pixels
    x = []
    y = []
    for n in range(10):
        x.append(random.randrange(sepiaimage.shape[0]))
        y.append(random.randrange(sepiaimage.shape[1]))
    # for each of the selected pixels assert if the RGB values are what they should be
    weights = [
        [ 0.393, 0.769, 0.189],
        [ 0.349, 0.686, 0.168],
        [ 0.272, 0.534, 0.131],
    ]
    for x,y in zip(x,y):
        sepiaR = image[x,y,0] * weights[0][0] + image[x,y,1] * weights[0][1] + image[x,y,2] * weights[0][2]
        sepiaG = image[x,y,0] * weights[1][0] + image[x,y,1] * weights[1][1] + image[x,y,2] * weights[1][2]
        sepiaB = image[x,y,0] * weights[2][0] + image[x,y,1] * weights[2][1] + image[x,y,2] * weights[2][2]
        assert sepiaimage[x,y,0] == np.uint8(min(255,sepiaR)) and sepiaimage[x,y,1] == np.uint8(min(255,sepiaG)) and sepiaimage[x,y,2] == np.uint8(min(255,sepiaB))
    
    # check if the returned image of numpy_color2sepia is equal to the reference sepia image
    assert np.allclose(sepiaimage, reference_sepia)
