![](https://i.imgur.com/ggO3unV.png)
---
[![Build Status](https://travis-ci.org/george-kampanos/giraffe.svg?branch=master)](https://travis-ci.org/george-kampanos/giraffe)

Giraffe is a simple simulation of a 2D Bulk Storage. It can be used to demonstrate the performance and behaviour of different one-in-one-out algorithms within a warehouse.

The Bulk Storage consists of 2 aisles with 58 locations each and 2 Pick & Drop (P&D) points. The driver starts from the P&D with a Store instruction. Upon storing the TSU, the next task is a Rretrieve instruction based on the selected Mode.

There are 5 one-in-one-out algorithms included with Giraffe: 
- `CLOSEST_IN_AISLE`: Retrieve a TSU close to the driver and in the same aisle.
- `CLOSEST_AVAILABLE`: Retrieve the TSU closest to the driver.
- `HIGHEST_PRIORITY_IN_AISLE`: Retrieve the TSU with the highest priority in the same aisle as the driver.
- `HIGHEST_PRIORITY_AVAILABLE`: Retrieve the TSU with the highest priority within Bulk Storage. 
- `HIGHEST_PRIORITY_ON_WAY_OUT`: Retrieve the TSU with the highest priority that it is on the driver's way out. (_Under development_) 

### Demonstration
##### Closest Available
![](https://media.giphy.com/media/x02KOzHSQYLaAVgxY5/giphy.gif)

##### Closest in Aisle
![](https://media.giphy.com/media/9xwLCJEpJ9v1BII0ko/giphy.gif)

##### Highest Priority in Aisle
![](https://media.giphy.com/media/3IFCk1JPDGFg6xfyiS/giphy.gif)

##### Highest Priority Available
![](https://media.giphy.com/media/35zMBNqSko71IQbwJi/giphy.gif)

### Libraries
Giraffe has been written entirely in Kotlin and uses the following dependencies: 
- [TornadoFX](https://tornadofx.io/) for the animations,
- [Spek](http://spekframework.org), [jUnit 5](https://junit.org/junit5/) & [mockK](https://github.com/mockk/mockk) for testing.

![](https://i.imgur.com/Ol0BpK7.jpg)
