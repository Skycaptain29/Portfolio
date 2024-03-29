""" Test script executing all the necessary unit tests for the functions in analytic_tools/utilities.py module
    which is a part of the analytic_tools package
"""

# Include the necessary packages here
from pathlib import Path

import pytest
import os

# This should work if analytic_tools has been installed properly in your environment
from analytic_tools.utilities import (
    get_dest_dir_from_csv_file,
    get_diagnostics,
    is_gas_csv,
    merge_parent_and_basename,
    display_diagnostics,
    display_directory_tree
)


@pytest.mark.task12
def test_get_diagnostics(example_config):
    """Test functionality of get_diagnostics in utilities module

    Parameters:
        example_config (pytest fixture): a preconfigured temporary directory containing the example configuration
                                     from Figure 1 in assignment2.md

    Returns:
    None
    """
    # Remove if you implement this task
    #raise NotImplementedError("Remove me if you implement this mandatory task")
    dir = get_diagnostics(example_config)
    
    assert dir["subdirectories"] == 4
    assert dir["files"] == 11
    assert dir[".csv files"] == 8
    assert dir[".txt files"] == 0
    assert dir[".npy files"] == 2
    assert dir[".md files"] == 0
    assert dir["other files"] == 0


@pytest.mark.task12
@pytest.mark.parametrize(
    "exception, dir",
    [
        (NotADirectoryError, "Not_a_real_directory"),
        (NotADirectoryError, "abc.txt"),
        (TypeError, 123),
        # add more combinations of (exception, dir) here
    ],
)
def test_get_diagnostics_exceptions(exception, dir):
    """Test the error handling of get_diagnostics function

    Parameters:
        exception (concrete exception): The exception to raise
        dir (str or pathlib.Path): The parameter to pass as 'dir' to the function

    Returns:
        None
    """
    # Remove if you implement this task
    #raise NotImplementedError("Remove me if you implement this mandatory task")
    with pytest.raises(exception):
        get_diagnostics(dir)

def test_get_diagnostics(example_config):
    """Test functionality of display_diagnostics in utilities module

    Parameters:
        example_config (pytest fixture): a preconfigured temporary directory containing the example configuration
                                     from Figure 1 in assignment2.md

    Returns:
    None
    """
    path = Path(example_config)

    Dict = get_diagnostics(path)

    display_diagnostics(path,Dict)

@pytest.mark.task12
def test_display_directory_tree(example_config):
    """Test functionality of display_directory_tree in utilities module

    Parameters:
        example_config (pytest fixture): a preconfigured temporary directory containing the example configuration
                                     from Figure 1 in assignment2.md

    Returns:
    None
    """
    display_directory_tree(example_config)



@pytest.mark.task22
def test_is_gas_csv():
    """Test functionality of is_gas_csv from utilities module

    Parameters:
        None

    Returns:
        None
    """
    # Remove if you implement this task
    #raise NotImplementedError("Remove me if you implement this mandatory task")
    dir = Path(__file__).parent / "pollution_data" / "by_src" / "src_agriculture" / "CH4.csv"

    ret = is_gas_csv(dir)
    assert isinstance(ret, bool)
    assert ret

    


@pytest.mark.task22
@pytest.mark.parametrize(
    "exception, path",
    [
        (ValueError, Path(__file__).parent.absolute()),
        (ValueError, Path(__file__).parent / "pollution_data" / "by_src" / "src_agriculture"),
        (ValueError, Path(__file__).parent / "pollution_data" / "by_src" / "src_agriculture" / "CH4_198.npy"),
        (TypeError, 2)
        # add more combinations of (exception, path) here
    ],
)
def test_is_gas_csv_exceptions(exception, path):
    """Test the error handling of is_gas_csv function

    Parameters:
        exception (concrete exception): The exception to raise
        path (str or pathlib.Path): The parameter to pass as 'path' to function

    Returns:
        None
    """
    # Remove if you implement this task
    #raise NotImplementedError("Remove me if you implement this mandatory task")
    with pytest.raises(exception): 
        is_gas_csv(path)


@pytest.mark.task24
def test_get_dest_dir_from_csv_file(example_config):
    """Test functionality of get_dest_dir_from_csv_file in utilities module.

    Parameters:
        example_config (pytest fixture): a preconfigured temporary directory containing the example configuration
            from Figure 1 in assignment2.md

    Returns:
        None
    """
    # Remove if you implement this task
    #raise NotImplementedError("Remove me if you implement this mandatory task")
    path = Path(example_config)
    
    dest_parent = path / "pollution_data_restructured" / "by_gas"

    dest_parent.mkdir(parents=True)

    file = path / "pollution_data" / "by_src" / "src_agriculture" / "H2.csv"

    

    assert get_dest_dir_from_csv_file(dest_parent, file) == path / "pollution_data_restructured" / "by_gas" / "gas_H2"


@pytest.mark.task24
@pytest.mark.parametrize(
    "exception, dest_parent, file_path",
    [
        (ValueError, Path(__file__).parent.absolute(), "foo.txt"),
        (TypeError, 2, Path(__file__).parent.absolute() / "pollution_data" / "by_src" / "src_agriculture" / "H2.csv"),
        (ValueError, Path(__file__).parent.absolute(), Path(__file__).parent.absolute()),
        (ValueError, Path(__file__).parent.absolute() / "ABC", "foo.txt")
        # add more combinations of (exception, dest_parent, file_path) here
    ],
)
def test_get_dest_dir_from_csv_file_exceptions(exception, dest_parent, file_path):
    """Test the error handling of get_dest_dir_from_csv_file function

    Parameters:
        exception (concrete exception): The exception to raise
        dest_parent (str or pathlib.Path): The parameter to pass as 'dest_parent' to the function
        file_path (str or pathlib.Path): The parameter to pass as 'file_path' to the function

    Returns:
        None
    """
    # Remove if you implement this task
    #raise NotImplementedError("Remove me if you implement this mandatory task")
    with pytest.raises(exception):
        get_dest_dir_from_csv_file(dest_parent, file_path)


@pytest.mark.task26
def test_merge_parent_and_basename():
    """Test functionality of merge_parent_and_basename from utilities module

    Parameters:
        None

    Returns:
        None
    """
    # Remove if you implement this task
    #raise NotImplementedError("Remove me if you implement this mandatory task")
    
    inputs = [Path(__file__).parent.absolute() / "pollution_data" / "by_src" / "src_agriculture" / "H2.csv",
             "some_folder/some_subfolder",
             "Some_Dir/some_file.txt"]
    for string in inputs:
        assert merge_parent_and_basename(string) == Path(string).parent.name.replace(os.sep, "_") + "_" + Path(string).name
   


@pytest.mark.task26
@pytest.mark.parametrize(
    "exception, path",
    [
        (TypeError, 33),
        (ValueError, "some_file.txt"),
        (TypeError, True),
        # add more combinations of (exception, path) here
    ],
)
def test_merge_parent_and_basename_exceptions(exception, path):
    """Test the error handling of merge_parent_and_basename function

    Parameters:
        exception (concrete exception): The exception to raise
        path (str or pathlib.Path): The parameter to pass as 'pass' to the function

    Returns:
        None
    """
    # Remove if you implement this task
    #raise NotImplementedError("Remove me if you implement this mandatory task")
    with pytest.raises(exception):
        merge_parent_and_basename(path)
