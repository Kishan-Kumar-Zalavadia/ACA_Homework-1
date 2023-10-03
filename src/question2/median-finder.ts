import * as fs from "fs";

class MedianFinder {
  // Function to merge N sorted arrays.
  static mergeNSortedArrays(arrays: number[][]): number[] {
    const result: number[] = [];
    const indices: number[] = new Array(arrays.length).fill(0);
    let allArraysEmpty = false;

    const startTime = process.hrtime.bigint!();

    while (!allArraysEmpty) {
      allArraysEmpty = true;
      let minValue = Number.MAX_VALUE;
      let minIndex = -1;

      for (let i = 0; i < arrays.length; i++) {
        if (indices[i] < arrays[i].length) {
          allArraysEmpty = false;
          const currentValue = arrays[i][indices[i]];
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

    const endTime = process.hrtime.bigint!();
    const runTime = Number(endTime - startTime);
    console.log(
      "_____________________________________________________________________________________"
    );
    console.log("Run Time: " + runTime + " nanoseconds");
    return result;
  }

  // Function to find the median of a sorted list.
  findMedian(sortedList: number[]): number {
    const totalSize = sortedList.length;
    const middle = Math.floor(totalSize / 2);

    if (totalSize % 2 === 0) {
      // If even number of elements, the median is the average of the two middle elements.
      return (sortedList[middle - 1] + sortedList[middle]) / 2;
    } else {
      // If an odd number of elements, the median is the middle element.
      return sortedList[middle];
    }
  }

  public static main(): void {
    const inputFilePath =
      "/Users/kishankumarz/Library/CloudStorage/GoogleDrive-kishankumarz3131@gmail.com/My Drive/Important/[02] USA/[02] UNT/[02] Classes/[01] Analysis_of Computer_Algoritms/[02] ACA_Assisgnment/[01] HomeWork/ACA_HomeWork-1/src/question2/output.txt";
    const fileData = fs.readFileSync(inputFilePath, "utf8");
    const inputSortedList: number[][] = [];

    const lines = fileData.split("\n");

    for (const line of lines) {
      if (line.trim() === "") {
        continue; // Skip empty lines
      }

      const values = line.split(" ");
      const innerList: number[] = [];

      for (const value of values) {
        const parsedValue = parseInt(value, 10);
        if (!isNaN(parsedValue)) {
          innerList.push(parsedValue);
        }
      }

      inputSortedList.push(innerList);
    }

    console.log(
      "_____________________________________________________________________________________"
    );
    console.log("Input: " + JSON.stringify(inputSortedList));

    const mergedList = MedianFinder.mergeNSortedArrays(inputSortedList);
    console.log("Sorted Array: " + JSON.stringify(mergedList));

    const finder = new MedianFinder();
    const median = finder.findMedian(mergedList);
    console.log("Median: " + median);
    console.log(
      "_____________________________________________________________________________________"
    );
  }
}

MedianFinder.main();
