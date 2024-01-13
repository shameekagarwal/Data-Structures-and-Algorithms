# Patterns

- outer loop - rows
- inner loop - columns
- so, a nested loop is needed - this should be enough to solve all patterns

# N-Forest

- https://www.codingninjas.com/studio/problems/n-forest_6570177

```java
public class Solution {
    public static void nForest(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("* ");
            }
            System.out.printf("\n");
        }
    }
}
```

# N/2-Forest

- https://www.codingninjas.com/studio/problems/n-2-forest_6570178

```java
public class Solution {
    public static void nForest(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.printf("* ");
            }
            System.out.printf("\n");
        }
    }
}
```

# N-Triangles

- https://www.codingninjas.com/studio/problems/n-triangles_6573689

```java
public class Solution {
    public static void nTriangle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.printf("%d ", j + 1);
            }
            System.out.printf("\n");
        }
    }
}
```

# Triangle

- https://www.codingninjas.com/studio/problems/triangle_6573690

```java
public class Solution {
    public static void nTriangle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.printf("%d ", i + 1);
            }
            System.out.printf("\n");
        }
    }
}
```

# Seeding

- https://www.codingninjas.com/studio/problems/seeding_6581892

```java
public class Solution {
    public static void seeding(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.printf("* ");
            }
            System.out.printf("\n");
        }
    }
}
```

# Reverse Number Triangle

- https://www.codingninjas.com/studio/problems/reverse-number-triangle_6581889

```java
public class Solution {
    public static void nNumberTriangle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.printf("%d ", j + 1);
            }
            System.out.printf("\n");
        }
    }
}
```

# Star Triangle

- https://www.codingninjas.com/studio/problems/star-triangle_6573671

```java
public class Solution {
    public static void nStarTriangle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                System.out.printf(" ");
            }
            for (int j = 0; j < (2 * i) + 1; j++) {
                System.out.printf("*");
            }
            System.out.printf("\n");
        }
    }
}
```

# Reverse Star Triangle

- https://www.codingninjas.com/studio/problems/reverse-star-triangle_6573685 

```java
public class Solution {
    public static void nStarTriangle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.printf(" ");
            }
            for (int j = 0; j < (2 * (n - i)) - 1; j++) {
                System.out.printf("*");
            }
            System.out.printf("\n");
        }
    }
}
```

# Star Diamond

- https://www.codingninjas.com/studio/problems/star-diamond_6573686

```java
public class Solution {
    public static void nStarDiamond(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                System.out.printf(" ");
            }
            for (int j = 0; j < (2 * i) + 1; j++) {
                System.out.printf("*");
            }
            System.out.printf("\n");
        }
        for (int i = n - 1; i > -1; i--) {
            for (int j = 0; j < n - 1 - i; j++) {
                System.out.printf(" ");
            }
            for (int j = 0; j < (2 * i) + 1; j++) {
                System.out.printf("*");
            }
            System.out.printf("\n");
        }
    }
}
```

# Rotated Triangle

- https://www.codingninjas.com/studio/problems/rotated-triangle_6573688

```java
public class Solution {
    public static void nStarTriangle(int n) {
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.printf("*");
            }
            for (int j = 0; j < n - 1 - i; j++) {
                System.out.printf(" ");
            }
            System.out.printf("\n");
        }
        
        for (int j = 0; j < n; j++) {
            System.out.printf("*");
        }
        System.out.printf("\n");
        
        for (int i = n - 2; i > - 1; i--) {
            for (int j = 0; j < i + 1; j++) {
                System.out.printf("*");
            }
            for (int j = 0; j < n - 1 - i; j++) {
                System.out.printf(" ");
            }
            System.out.printf("\n");
        }
    }
}
```

# Binary Number Triangle

- https://www.codingninjas.com/studio/problems/binary-number-triangle_6581890

```java
public class Solution {
    public static void nBinaryTriangle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.printf("%d ", (1 - ((i % 2) ^ (j % 2))));
            }
            System.out.printf("\n");
        }
    }
}
```

# Number Crown

- https://www.codingninjas.com/studio/problems/number-crown_6581894

```java
public class Solution {
    public static void numberCrown(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.printf("%d ", j + 1);
            }
            for (int j = 0; j < 2 * (n - i - 1); j++) {
                System.out.printf(" ");
            }
            for (int j = i; j > -1; j--) {
                System.out.printf("%d ", j + 1);
            }
            System.out.printf("\n");
        }
    }
}
```

# Increasing Number Triangle

- https://www.codingninjas.com/studio/problems/increasing-number-triangle_6581893

```java
public class Solution {
    public static void nNumberTriangle(int n) {
        int currentCount = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.printf("%d ", currentCount);
                currentCount += 1;
            }
            System.out.printf("\n");
        }
    }
}
```

# Increasing Letter Triangle

- https://www.codingninjas.com/studio/problems/increasing-letter-triangle_6581897

```java
public class Solution {
    public static void nLetterTriangle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.printf("%c ", (char)('A' + j));
            }
            System.out.printf("\n");
        }
    }
}
```

# Reverse Letter Triangle

- https://www.codingninjas.com/studio/problems/reverse-letter-triangle_6581906

```java
public class Solution {
    public static void nLetterTriangle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.printf("%c ", (char)('A' + j));
            }
            System.out.printf("\n");
        }
    }
}
```

# Alpha-Ramp

- https://www.codingninjas.com/studio/problems/alpha-ramp_6581888

```java
public class Solution {
    public static void alphaRamp(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.printf("%c ", (char)('A' + i));
            }
            System.out.printf("\n");
        }
    }
}
```

# Alpha Hill

- https://www.codingninjas.com/studio/problems/alpha-hill_6581921

```java
public class Solution {
    public static void alphaHill(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                System.out.printf(" ");
            }
            for (int j = 0; j < i + 1; j++) {
                System.out.printf("%c ", (char)('A' + j));
            }
            for (int j = i - 1; j > -1; j--) {
                System.out.printf("%c ", (char)('A' + j));
            }
            System.out.printf("\n");
        }
    }
}
```

# Alpha-Triangle

- https://www.codingninjas.com/studio/problems/alpha-triangle_6581429

```java
public class Solution {
    public static void alphaTriangle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > n - i - 2; j--) {
                System.out.printf("%c ", (char)('A' + j));
            }
            System.out.printf("\n");
        }
    }
}
```

# Symmetric Void

- https://www.codingninjas.com/studio/problems/symmetric-void_6581919

```java
public class Solution {
    public static void symmetry(int n) {
        
        StringBuffer sb = new StringBuffer("");
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                sb.append("* ");
            }
            for (int j = 0; j < 2 * i; j++) {
                sb.append(" ");
            }
            for (int j = 0; j < n - i; j++) {
                sb.append("* ");
            }
            sb.append("\n");
        }

        for (int i = n - 1; i > -1; i--) {
            for (int j = 0; j < n - i; j++) {
                sb.append("* ");
            }
            for (int j = 0; j < 2 * i; j++) {
                sb.append(" ");
            }
            for (int j = 0; j < n - i; j++) {
                sb.append("* ");
            }
            sb.append("\n");
        }
        
        System.out.printf(sb.toString());
    }
}
```

# Symmetry

- https://www.codingninjas.com/studio/problems/symmetry_6581914

```java
public class Solution {
    public static void symmetry(int n) {
        
        StringBuffer sb = new StringBuffer("");
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                sb.append("* ");
            }
            for (int j = 0; j < 2 * (n - i - 1); j++) {
                sb.append(" ");
            }
            for (int j = 0; j < i + 1; j++) {
                sb.append("* ");
            }
            sb.append("\n");
        }

        for (int i = n - 2; i > -1; i--) {
            for (int j = 0; j < i + 1; j++) {
                sb.append("* ");
            }
            for (int j = 0; j < 2 * (n - i - 1); j++) {
                sb.append(" ");
            }
            for (int j = 0; j < i + 1; j++) {
                sb.append("* ");
            }
            sb.append("\n");
        }

        System.out.printf(sb.toString());
    }
}
```

# Ninja And The Star Pattern I

- https://www.codingninjas.com/studio/problems/ninja-and-the-star-pattern-i_6581920

```java
public class Solution {
    public static void getStarPattern(int n) {
        
        StringBuffer sb = new StringBuffer("");

        for (int i = 0; i < n; i++) {
            sb.append("*");
        }
        sb.append("\n");

        if (n == 1) {
            System.out.printf(sb.toString());
            return;
        }

        for (int i = 0; i < n - 2; i++) {
            sb.append("*");
            for (int j = 0; j < n - 2; j++) {
                sb.append(" ");
            }
            sb.append("*\n");
        }

        for (int i = 0; i < n; i++) {
            sb.append("*");
        }
        sb.append("\n");

        System.out.printf(sb.toString());
    }
}
```

# Ninja And The Number Pattern

- https://www.codingninjas.com/studio/problems/ninja-and-the-number-pattern-i_6581959

```java
public class Solution {
    public static void getNumberPattern(int n) {
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < (2 * n) - 1; i++) {
            for (int j = 0; j < (2 * n) - 1; j++) {
                int distanceFromLeft = j;
                int distanceFromRight = (2 * n) - j - 2;
                int distanceFromUp = i;
                int distanceFromDown = (2 * n) - i - 2;
                int toSubtract = Math.min(distanceFromLeft, Math.min(distanceFromRight, Math.min(distanceFromUp, distanceFromDown)));
                sb.append(n - toSubtract);
            }
            sb.append("\n");
        }
        System.out.printf(sb.toString());
    }
}
```
