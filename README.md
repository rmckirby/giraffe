![](https://i.imgur.com/ggO3unV.png)
---
[![Build Status](https://travis-ci.org/george-kampanos/giraffe.svg?branch=master)](https://travis-ci.org/george-kampanos/giraffe)

Giraffe is a simple simulation of a 2D Bulk Storage. It can be used to demonstrate the performance of different one-in-one-out algoirthms within a VNA. 

There are 5 one-in-one-out algorithms included with Giraffe: 
- `CLOSEST_IN_AISLE`: Retrieve a TSU closest to the driver.
- `CLOSEST_AVAILABLE`: Retrieve any TSU closest to the driver.
- `HIGHEST_PRIORITY_IN_AISLE`: Retrieve the TSU with the highest priority in the same aisle as the driver.
- `HIGHEST_PRIORITY_AVAILABLE`: Retrieve the TSU with the highest priority within Bulk Storage. 
- `HIGHEST_PRIORITY_ON_WAY_OUT`: Retrieve the TSU with the highest priority that is on the way out. (_Under development_) 

### Demonstration
_coming soon..._

### Libraries
Giraffe has been written entirely in Kotlin and uses the following dependencies: 
- [TornadoFX](https://tornadofx.io/) for the animations,
- [Spek](http://spekframework.org), [jUnit 5](https://junit.org/junit5/) & [mockK](https://github.com/mockk/mockk) for testing.
