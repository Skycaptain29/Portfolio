"""
Task 1.1 - requesting HTML documents with HTTP
"""
from __future__ import annotations

import requests


def get_html(url: str, params: dict | None = None, output: str | None = None):
    """Get an HTML page and return its contents.

    Args:
        url (str):
            The URL to retrieve.
        params (dict, optional):
            URL parameters to add.
        output (str, optional):
            (optional) path where output should be saved.
    Returns:
        html (str):
            The HTML of the page, as text.
    """

    if params:
        url += "?"
        for key in params:
            url += key + "=" + params[key] + "&"


    # passing the optional parameters argument to the get function
    response = requests.get(url)
    if response:
        html_str = response.text

    if output:
        # if output is specified, the response url and text content are written to
        # the file `output`
        with open(f'{output}', 'w') as f:
            f.write(url)
            f.write(html_str)
    if response:
        return html_str
    else:
        return None
