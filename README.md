# CFG Epsilon and Unit Rule Elimination

This Java program performs epsilon and unit rule elimination on a context-free grammar (CFG). It provides functionality to parse a CFG, eliminate epsilon rules, and eliminate unit rules.

## Features

- Parses a formatted string representation of a CFG.
- Eliminates epsilon rules from the CFG.
- Eliminates unit rules from the CFG.

## Input Format

The input string representation of the CFG should follow the following format:

```
Variables#Terminals#Rules
```

- Variables: A semicolon-separated list of variables.
- Terminals: A semicolon-separated list of terminals.
- Rules: A semicolon-separated list of rules, where each rule is of the form `Variable/Production1,Production2,...`.

Example input:

```
S;A;B;C#a;b;c;d;x#S/aAb,xB;A/Bc,C,c,d;B/CACA,e;C/A,b,e
```

## Output

The program outputs the modified CFG after eliminating epsilon and unit rules.

## Authors

- [Mohamed Moustafa](https://github.com/alafty)
