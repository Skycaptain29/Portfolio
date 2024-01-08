"""
Bonus task
"""
from __future__ import annotations
from requesting_urls import get_html
from filter_urls import find_articles
import sys
import time
import queue
from concurrent.futures import ProcessPoolExecutor, wait, ThreadPoolExecutor
from multiprocessing import Manager
from concurrent.futures import FIRST_COMPLETED
from anytree import Node, RenderTree
import re

max_processes = 10

def find_path(start: str, finish: str) -> list[str]:
    """Find the shortest path from `start` to `finish`

    Arguments:
      start (str): wikipedia article URL to start from
      finish (str): wikipedia article URL to stop at

    Returns:
      path (list[str]):
        List of URLs representing the path from `start` to `finish`.
        The first item should be `start`.
        The last item should be `finish`.
        All items of the list should be URLs for wikipedia articles.
        Each article should have a direct link to the next article in the list.
    """
    #raise NotImplementedError("remove me to begin task")
    path = []
    path.append(start)
    path.append(finish)
    time_before = time.time_ns()

    with Manager() as manager:
        event = manager.Event() # create the shared event between the processes
        with ProcessPoolExecutor(max_processes) as executor:
            futures = [executor.submit(BFS, start, finish, i, event) for i in range(1, max_processes+1)] # call max_processes processes on the BFS function
            done, not_done = wait(futures, return_when=FIRST_COMPLETED) # wait for the first one to return
            future = done.pop() # get the process that returned
            result = future.result() # get the result
            event.set() # set the end event

    time_after = time.time_ns()

    tot_time = (time_after-time_before) / 1000000000 # calculate the runtime
    print("Ended")
    path = []
    for node in result.ancestors: # create the path based on the node ancestors
        path.append(node.name)
    path.append(finish)
    print(f"Found path {path} from page {start} to page {finish} in {tot_time} secs") #print the path
    assert path[0] == start
    assert path[-1] == finish
    return path # return the path

def BFS(start: str, end: str, process_n: int, event) -> Node:
    """Execute a Breadth First Search starting from the Start link, until the end-link is found

    Arguments:
      start (str): wikipedia article URL to start from
      finish (str): wikipedia article URL to stop at
      process_n (int): the number of the process that is running
      event (Event): flag shared between the processes to stop them when the path is found
      

    Returns:
      node_end (Node(string, parent)):
        the node linked to the end page
    """
    linksQueue = queue.Queue()
    html = get_html(start) # Get the html
    links = find_articles(html) # Filter the links
    Root = Node(start) # Root node
    visitedList = [] # List of already visited links

    english_pattern = re.compile(r"en.wikipedia.org", re.IGNORECASE) # Filter for the english wikipedia pages

    # Algorithm to divide the total links based on the number of processes
    set_start = int(len(links)/max_processes)*int(process_n-1)
    if process_n < max_processes:
        set_end = int(len(links)/max_processes)*int(process_n)
    else:
        set_end = len(links)

    links_thread = list(links)[set_start:set_end] # List of links for each process
    for link in links_thread:
        isEnglish = english_pattern.search(link) # Scan the link
        if isEnglish:
            node = Node(link, parent=Root) # If link is english, create the node with parent Root
            linksQueue.put(node) #, put the link in the queue


    flagEnd = False
    number = 0
    
    # loop over the queue until it is either empty, the flag_end is raised or the event is set
    while not linksQueue.empty() and not flagEnd and not event.is_set():
        node = linksQueue.get() # get the node
        link = node.name # extract the link
        if link == end: # if the link is the end link quit the loop and return the node
            flagEnd = True
            node_end = node            
        elif not link in visitedList: # if the current link has not been already visited
            visitedList.append(link) # add the current link to the visited list
            linkHtml = get_html(link) # get the html
            if linkHtml:
                subLinks = find_articles(linkHtml)# get the links and iterate trough them
                for subLink in subLinks: # for each link in the curren page
                    isEnglish = english_pattern.search(subLink)
                    if isEnglish: # if the link is a english wiki page
                        subNode = Node(subLink, parent= node) # create the node for the link, with parent = parent_node
                        linksQueue.put(subNode) # put the newly create node in the queue
                number += 1               
                sys.stdout.write("process {1}: scanned pages: {0}\n".format(number, process_n))
    return node_end

   
if __name__ == "__main__":
    start = "https://en.wikipedia.org/wiki/Nobel_Prize"
    #finish = "https://en.wikipedia.org/wiki/Norwegian_language"
    finish = "https://en.wikipedia.org/wiki/Martin_Luther"
    #finish = "https://en.wikipedia.org/wiki/Array_data_structure."
    find_path(start, finish)
    
