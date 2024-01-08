"""
Task 1.2, 1.3

Filtering URLs from HTML
"""

from __future__ import annotations

import re
from urllib.parse import urljoin, urlparse


def find_urls(
    html: str,
    base_url: str = "https://en.wikipedia.org",
    output: str | None = None,
) -> set[str]:
    """
    Find all the url links in a html text using regex

    Arguments:
        html (str): html string to parse
        base_url (str): the base url to the wikipedia.org pages
        output (Optional[str]): file to write to if wanted
    Returns:
        urls (Set[str]) : set with all the urls found in html text
    """
    # create and compile regular expression(s)

    patternA = re.compile(r"<a[^>]+>", flags=re.IGNORECASE)
    # src finds the text between quotes of the `src` attribute
    patternURL = re.compile(r'href=\"([^\"]+)"', flags=re.IGNORECASE)

    patternInternal = re.compile(r'#[\w-]+', flags=re.IGNORECASE)

    patternSlash = re.compile(r"^//", flags=re.IGNORECASE)

    # 1. find all the anchor tags, then
    # 2. find the urls href attributes
    url_set = set()

    # first, find all the img tags
    for a_tag in patternA.findall(html):
        # then, find the src attribute of the img, if any
        match = patternURL.search(a_tag)
        if match:
            match = match.group(1)
            if not match[0] == "#":
                internal = patternInternal.search(match)
                if internal:
                    match = re.sub(patternInternal, "", match)
            else:
                continue
            if not patternSlash.search(match) and match[0] == "/":
                url_set.add(base_url+match)
            elif patternSlash.search(match):
                url_set.add("https:"+match)
            else:
                url_set.add(match)
    

    # Write to file if requested
    if output:
        print(f"Writing to: {output}")
        with open(f'{output}', 'w') as f:
            f.write(url_set)

    return url_set


def find_articles(html: str, output: str | None = None) -> set[str]:
    """Finds all the wiki articles inside a html text. Make call to find urls, and filter
    arguments:
        - text (str) : the html text to parse
        - output (str, optional): the file to write the output to if wanted
    returns:
        - (Set[str]) : a set with urls to all the articles found
    """
    urls = find_urls(html)
    pattern = re.compile(r"https:\/\/\w{2}.wikipedia.org\/wiki\/[^:]+(:{1}.+)?", flags=re.IGNORECASE)
    articles = set()

    for link in urls:
        article = pattern.search(link)
        if article and article.group(1) == None:
            articles.add(article.group(0))
            

        

    # Write to file if wanted
    if output:
        print(f"Writing to: {output}")
        with open(f'{output}', 'w') as f:
            for article in articles:
                f.write(article)

    return articles


## Regex example
def find_img_src(html: str):
    """Find all src attributes of img tags in an HTML string

    Args:
        html (str): A string containing some HTML.

    Returns:
        src_set (set): A set of strings containing image URLs

    The set contains every found src attribute of an img tag in the given HTML.
    """
    # img_pat finds all the <img alt="..." src="..."> snippets
    # this finds <img and collects everything up to the closing '>'
    img_pat = re.compile(r"<img[^>]+>", flags=re.IGNORECASE)
    # src finds the text between quotes of the `src` attribute
    src_pat = re.compile(r'src="([^"]+)"', flags=re.IGNORECASE)
    src_set = set()
    # first, find all the img tags
    for img_tag in img_pat.findall(html):
        # then, find the src attribute of the img, if any
        match = src_pat.search(img_tag)
        if match:
            src_set.add(match.group(1))
    return src_set