# Packages

- given list of packages weight, height, width, length, calculate transport charges, service charges
- given couple of interfaces and methods to find transport charges and service charges by package type and for overall cargo

| package type | transport | service charges |
| ------------ | --------- | --------------- |
| Standard | 0.5 * volume | 0.5 * wight + 0.5 * distance |
| Hazardous | 0.75 * volume | 0.75 * wight + 0.75 * distance |
| Fragile | 0.625 * volume | 0.625 * wight + 0.625 * distance |

```java
interface Ipackage {
  getters and setters
}
```

```java
interface IShipment {

  void insert(Ipackage pkg)

  void remove(int id)

  Map<String, Integer> getTotalCosts()

  List getPackages()
}
```

```java
class Package implements Ipackage {

}
```

```java
class Cargo implements IShipment {

}
```

## Solution

- use "composite pattern" for cost of individual package vs shipment
- use "factory pattern" for individual package types - an enum for the package types, and different classes for the different package types
- can use "template method pattern" - the different classes return transport and service charges separately, while the overall cost is returned at one go
