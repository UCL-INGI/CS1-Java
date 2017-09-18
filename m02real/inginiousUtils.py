#!/bin/env/python3

from os import listdir, system
import zipfile

def listDiff(a, b):
    """Returns a list containing the elements in a but not in b"""
    b = {bb.lower() for bb in b}
    return [aa for aa in a if aa.lower() not in b]

def get_rst_title(title):
    """Returns a title formatted for restructured text
    (i.e. with the correct amount of '=' Above and below the title)"""
    upperTitle = "=" * len(title) + "\n"
    middleTitle = title + "\n"
    lowerTitle = "=" * len(title)
    return upperTitle + middleTitle + lowerTitle

def extract_zip_content(zipPath, expectedFiles, extractPath="student/"):
    """Returns a string which is empty if the zip archive located at zipPath
    did contain all the files in expecetFiles. If the zip archive didn't contain
    all the zip files, the returned string contains a restructuredText formatted
    description of the expected and missing files."""
    output = ""
    with zipfile.ZipFile(zipPath, 'r') as myzip:
        fileList = myzip.namelist()
        missingFiles = listDiff(expectedFiles, fileList)
        if missingFiles:
            output += "Your archive is missing some files at its root. Here is the list of the files at the root of your archive:\n\n"
            for fileName in fileList:
                output += "- :bash:\`" + fileName + "\`\n"
            output += "\nHere is the list of files expected at the root of your archive:\n\n"
            for fileName in expectedFiles:
                output += "- :bash:\`" + fileName + "\`\n"
        else:
            myzip.extractall(extractPath)
    return output
