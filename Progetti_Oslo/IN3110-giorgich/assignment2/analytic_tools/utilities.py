"""Module containing functions used to achieve the desired restructuring of the pollution_data directory
"""
# Include the necessary packages here
from pathlib import Path
from typing import Dict, List
import os


def get_diagnostics(dir: str | Path) -> Dict[str, int]:
    """Get diagnostics for the directory tree, with root directory pointed to by dir.
       Counts up all the files, subdirectories, and specifically .csv, .txt, .npy, .md and other files in the whole directory tree.

    Parameters:
        dir (str or pathlib.Path) : Absolute path to the directory of interest

    Returns:
        res (Dict[str, int]) : a dictionary of the findings with following keys: files, subdirectories, .csv files, .txt files, .npy files, .md files, other files.

    """

    # Remove if you implement this task
    #raise NotImplementedError("Remove me if you implement this mandatory task")

    # Dictionary to return
    res = {
        "files": 0,
        "subdirectories": 0,
        ".csv files": 0,
        ".txt files": 0,
        ".npy files": 0,
        ".md files": 0,
        "other files": 0,
    }

    # Remember error handling
    path = Path(dir)

    if not path.exists():
        raise NotADirectoryError("The path points to a non existant directory")

    if not path.is_dir():
        raise NotADirectoryError("The path does not point to a directory")


    # Traverse the directory and find its contents
    contents = path.rglob("*")

    # Count folders and total num. of files
    for path in contents:
        if path.is_dir():
            res["subdirectories"] += 1
        else:
            res["files"] += 1
            if path.suffix == ".csv":
                res[".csv files"] += 1
            elif path.suffix == ".txt":
                res[".txt files"] += 1
            elif path.suffix == ".npy":
                res[".npy files"] += 1
            elif path.suffix == ".md":
                res[".md files"] += 1
            elif path.is_file():
                res["other files"] += 1
            else:
                res["files"] -= 1
 
    return res


def display_diagnostics(dir: str | Path, contents: Dict[str, int]) -> None:
    """Display diagnostics for the directory tree, with root directory pointed to by dir.
        Objects to display: files, subdirectories, .csv files, .txt files, .npy files, .md files, other files.

    Parameters:
        dir (str or pathlib.Path) : Absolute path the directory of interest
        contents (Dict[str, int]) : a dictionary of the same type as return type of get_diagnostics, has the form:

            .. highlight:: python
            .. code-block:: python

                {
                    "files": 0,
                    "subdirectories": 0,
                    ".csv files": 0,
                    ".txt files": 0,
                    ".npy files": 0,
                    ".md files": 0,
                    "other files": 0,
                }

    Returns:
        None
    """
    # Remove if you implement this task
    #raise NotImplementedError("Remove me if you implement this mandatory task")

    #Check for exeptions
    path = Path(dir)

    if not path.exists:
        raise NotADirectoryError("The path points to a non existant directory")

    if not path.is_dir():
        raise NotADirectoryError("The path does not point to a directory")
    
    if not isinstance(contents, Dict):
        raise TypeError("The dictionary is of the wrong type")

    # Print the summary to the terminal
    print(dir)

    print("\033[96m~~~~~~~~~~~~~~~~~~~~~~~~\033[0m")
    for entry in contents:
        print(f"Number of {entry}: {contents[entry]:2}")
    print("\033[96m~~~~~~~~~~~~~~~~~~~~~~~~\033[0m")



def display_directory_tree(dir: str | Path, maxfiles: int = 3) -> None:
    """Display a directory tree, with root directory pointed to by dir.
       Limit the number of files to be displayed for convenience to maxfiles.

    Parameters:
        dir (str or pathlib.Path) : Absolute path to the directory of interest
        maxfiles (int) : Maximum number of files to be displayed at each level in the tree, default to three.

    Returns:
        None

    """
    # NOTE: This is a bonus task, if you implementing it, remove `raise NotImplementedError`
    #raise NotImplementedError("Remove me if you implement this bonus task")

    #Checking for exeptions
    path = Path(dir)
    
    if not path.exists:
        raise NotADirectoryError("The path points to a non existant directory")

    if not path.is_dir():
        raise NotADirectoryError("The path does not point to a directory")
    
    if not isinstance(maxfiles, int):
        raise TypeError("Maxvalue is not an integer")
    
    if maxfiles < 1:
        raise ValueError("Maxvalue is less than 1")
    
    #print the tree of the folder
    contents = path.rglob("*")
    tree = {}
    for path in contents:
        if path.is_dir():
            tree[path.name] = [path.name]
            if path.parent.absolute().name in tree:
                tree[path.parent.absolute().name].append(path.name)
        elif path.is_file():
            if path.parent.absolute().name in tree:
                tree[path.parent.absolute().name].append(path.name)
    level = 0

    res = list(tree.keys())[0]
    print_tree(tree, res, maxfiles, 0)


def print_tree(tree: Dict[str, list], current: str, maxfiles: int, level:int) -> int:
    """Prints a given tree recursively, automatically spacing the prints in accordance with the depth level

    Parameters:
        tree (Dictionary of [string, list]): the dictionary containing the objects contained in the directory. Each
              key is equivalent to a subfolder, and each subfolder contains itself for printing purposes.
        current (string): the current key of the recursive node
        maxfiles (integer): remaining number of files that can be printed in a specific subfolder
        level (integer): currend level of depth, used to print the spaces

    Returns:
        1 if current is a file
        0 elsewhere

    """
    if level == 0:
        print(current + "/")
        print_tree(tree, current, maxfiles, level+1)
    else:
        if current in tree:
            for elem in tree[current]:
                if not elem == current:
                    if maxfiles > 0:
                        print(level * 3 * " " + "- " + elem)
                    elif maxfiles == 0:
                        print(level * 3 * " "+"...")      
                    maxfiles -= print_tree(tree, elem, maxfiles, level+1)
        else:
            return 1
    return 0

    
    
    

def is_gas_csv(path: str | Path) -> bool:
    """Checks if a csv file pointed to by path is an original gas statistics file.
        An original file must be called '[gas_formula].csv' where [gas_formula] is
        in ['CO2', 'CH4', 'N2O', 'SF6', 'H2'].

    Parameters:
         - path (str of pathlib.Path) : Absolute path to .csv file that will be checked

    Returns
         - (bool) : Truth value of whether the file is an original gas file
    """
    # Remove if you implement this task
    #raise NotImplementedError("Remove me if you implement this mandatory task")

    # Do correct error handling first

    file = Path(path)

    if not file.suffix == ".csv":
        raise ValueError("Expecting a .csv file")
    
    # List of greenhouse gasses, correct filenames in front of a .csv ending
    gasses = ["CO2", "CH4", "N2O", "SF6", "H2"]

    for gas in gasses:
        if gas == file.stem:
            return True
    return False   
        

    


def get_dest_dir_from_csv_file(dest_parent: str | Path, file_path: str | Path) -> Path:
    """Given a file pointed to by file_path, derive the correct gas_[gas_formula] directory name.
        Checks if a directory "gas_[gas_formula]", exists and if not, it creates one as a subdirectory under dest_parent.

        The file pointed to by file_path must be a valid file. A valid file must be called '[gas_formula].csv' where [gas_formula]
        is in ['CO2', 'CH4', 'N2O', 'SF6', 'H2'].

    Parameters:
        - dest_parent (str or pathlib.Path) : Absolute path to parent directory where gas_[gas_formula] should/will exist
        - file_path (str or pathlib.Path) : Absolute path to file that gas_[gas_formula] directory will be derived from

    Returns:
        - (pathlib.Path) : Absolute path to the derived directory

    """
    # Remove if you implement this task
    #raise NotImplementedError("Remove me if you implement this mandatory task")

    # Do correct error handling first
    dest_parent = Path(dest_parent)
    file_path = Path(file_path)

    if not file_path.is_file():
        raise ValueError("file_path has to point to an existing file")

    if not is_gas_csv(file_path):
        raise ValueError("file_path does not point to an original .csv gas file")

    if not dest_parent.exists() or not dest_parent.is_dir():
        raise NotADirectoryError("dest_parent has to point to an existing directory")

    # If the input file is valid:
    # Derive the name of the directory, pattern: gas_[gas_formula] directory
    dest_name = f"gas_{file_path.stem}"

    """Restructured/gas_H20"""
    # Derive its absolute path
    dest_path = dest_parent / dest_name

    # Check if the directory already exists, and create one of not
    if dest_path.exists():
        return dest_path
    else:
        dest_path.mkdir()
        return dest_path


def merge_parent_and_basename(path: str | Path) -> str:
    """This function merges the basename and the parent-name of a path into one, uniting them with "_" character.
       It then returns the basename of the resulting path.

    Parameters:
        - path (str or pathlib.Path) : Absolute path to modify

    Returns:
        - new_base (str) : New basename of the path
    """
    # Remove if you implement this task
    #raise NotImplementedError("Remove me if you implement this mandatory task")

    path = Path(path)

    if path.parent.name == "":
        raise ValueError("Expected a path with a parent name")
    
    # New, merged, basename of the path, which will be the new filename
    new_base = path.parent.name.replace(os.sep, "_") + "_" + path.name
    return new_base     


def delete_directories(path_list: List[str | Path]) -> None:
    """Prompt the user for permission and delete the objects pointed to by the paths in path_list if
       permission is given. If the object is a directory, its whole directory tree is removed.

    Parameters:
        - path_list (List[str | Path]) : a list of absolute paths to all the objects to be removed.


    Returns:
    None
    """
    # NOTE: This is an optional task, no points assigned. If you are skipping it, remove `raise NotImplementedError` in the function body
    #raise NotImplementedError("Remove me if you implement this optional task")

    for path in path_list:
        dict = get_diagnostics(path)
        display_diagnostics(path, dict)
        display_directory_tree(path, 10)

        resp = input("Are you sure you want to Delete this directory tree? [Y/N]")

        if resp == "Y" or resp == "y":
            print("Deleting directory tree")
            rm_tree_recursive(path)
        else:
            print("Deletion canceled")

        

def rm_tree_recursive(path: str | Path) -> None:
    """Deletes recursively the directory tree of the path specified as parameter to the function
    
    Parameters:
        - path (str | Path): tree of folders to completely remove
        
    Returns:
        None"""
    path = Path(path)

    for element in path.glob("*"):
        if element.is_file():
            element.unlink()
        else:
            rm_tree_recursive(element)
    path.rmdir()