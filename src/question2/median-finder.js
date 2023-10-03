"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var fs = require("fs");
var MedianFinder = /** @class */ (function () {
    function MedianFinder() {
    }
    // Function to merge N sorted arrays.
    MedianFinder.mergeNSortedArrays = function (arrays) {
        var result = [];
        var indices = new Array(arrays.length).fill(0);
        var allArraysEmpty = false;
        var startTime = process.hrtime.bigint();
        while (!allArraysEmpty) {
            allArraysEmpty = true;
            var minValue = Number.MAX_VALUE;
            var minIndex = -1;
            for (var i = 0; i < arrays.length; i++) {
                if (indices[i] < arrays[i].length) {
                    allArraysEmpty = false;
                    var currentValue = arrays[i][indices[i]];
                    if (currentValue !== null && currentValue < minValue) {
                        minValue = currentValue;
                        minIndex = i;
                    }
                }
            }
            if (minIndex !== -1) {
                result.push(minValue);
                indices[minIndex]++;
            }
        }
        var endTime = process.hrtime.bigint();
        var runTime = Number(endTime - startTime);
        console.log("_____________________________________________________________________________________");
        console.log("Run Time: " + runTime + " nanoseconds");
        return result;
    };
    // Function to find the median of a sorted list.
    MedianFinder.prototype.findMedian = function (sortedList) {
        var totalSize = sortedList.length;
        var middle = Math.floor(totalSize / 2);
        if (totalSize % 2 === 0) {
            // If even number of elements, the median is the average of the two middle elements.
            return (sortedList[middle - 1] + sortedList[middle]) / 2;
        }
        else {
            // If an odd number of elements, the median is the middle element.
            return sortedList[middle];
        }
    };
    MedianFinder.main = function () {
        var inputFilePath = "/Users/kishankumarz/Library/CloudStorage/GoogleDrive-kishankumarz3131@gmail.com/My Drive/Important/[02] USA/[02] UNT/[02] Classes/[01] Analysis_of Computer_Algoritms/[02] ACA_Assisgnment/[01] HomeWork/ACA_HomeWork-1/src/question2/output.txt";
        var fileData = fs.readFileSync(inputFilePath, "utf8");
        var inputSortedList = [];
        var lines = fileData.split("\n");
        for (var _i = 0, lines_1 = lines; _i < lines_1.length; _i++) {
            var line = lines_1[_i];
            if (line.trim() === "") {
                continue; // Skip empty lines
            }
            var values = line.split(" ");
            var innerList = [];
            for (var _a = 0, values_1 = values; _a < values_1.length; _a++) {
                var value = values_1[_a];
                var parsedValue = parseInt(value, 10);
                if (!isNaN(parsedValue)) {
                    innerList.push(parsedValue);
                }
            }
            inputSortedList.push(innerList);
        }
        console.log("_____________________________________________________________________________________");
        console.log("Input: " + JSON.stringify(inputSortedList));
        var mergedList = MedianFinder.mergeNSortedArrays(inputSortedList);
        console.log("Sorted Array: " + JSON.stringify(mergedList));
        var finder = new MedianFinder();
        var median = finder.findMedian(mergedList);
        console.log("Median: " + median);
        console.log("_____________________________________________________________________________________");
    };
    return MedianFinder;
}());
MedianFinder.main();
