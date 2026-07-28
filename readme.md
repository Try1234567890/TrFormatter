# JSF (Java String Formatter)

## Overview

The JSF (Java String Formatter) is a library that allows you to manage strings in various ways, such as Cases, Searches,
Scanning and Dynamic Placeholders.   
The main feature of this library is the Dynamic Placeholders, which allows you to replace placeholders in strings with
values at runtime. Other features include:

- String Cases: Converting strings to different cases (e.g., camelCase, snake_case, PascalCase, Train-Case, etc.);
- String Searches: Searching for substrings within strings, including reverse searches or delimiter-based searches;
- String Scanning: Scanning strings for specific patterns; Patterns include: splitter, delimiters, balanced delimiters,
  prefixes, suffixes, etc.

## Dynamic Placeholders

### Parameters Types

1. `String`:
2. `Color`:
3. `Boolean`:
4. `Byte`:
5. `Short`:
6. `Integer`:
7. `Long`:
8. `Float`:
9. `Double`:
10. `Character`:
11. `List`:
12. `Map`:

### Actions

1. `read_file`:
    - Output: The content of the file read 
    - Parameters:
        - 1:
            - **Name**: `file_path`
            - **Description**: Specify the absolute file path to read
            - **Aliases**: [`file`, `path`, `fp`]
            - **Type**: String
            - **Required**: **true**
        - 2:
            - **Name**: `error_show`
            - **Description**: Specify the method to handle errors (e.g., throw exception, none, log to console, etc.)
            - **Aliases**: [`error`, `es`]
            - **Type**: String
            - **Default**: `EXCEPTION`
            - **Required**: **false**
            - **Options**: 
              - `NONE`: Do nothing and returns an empty string.
              - `EXCEPTION`: Thrown an exception and stops execution.
              - `LOG_CONSOLE`: Log the error to console and returns an empty string.

2. `console`:
    - Output: The message sent to console 
    - Parameters:
        - `message`:
            - **Description**: Specify the message sent to console (will be the function output too) 
            - **Aliases**: [`msg`, `m`]
            - **Type**: String
            - **Required**: **true**
        - `color`:
            - **Description**: Specify the text color
            - **Aliases**: [`c`]
            - **Type**: Color
            - **Default**: Empty
            - **Required**: **false**

3. `env`:
    - Output: The environment value if not null, otherwise an empty string. 
    - Parameters:
        - `variable`:
            - **Description**: Specify variable name to search between system environment
            - **Aliases**: [`var`, `v`]
            - **Type**: String
            - **Required**: **true**

4. `property`:
    - Output: The property value if not null, otherwise an empty string. 
    - Parameters:
        - `variable`:
            - **Description**: Specify variable name to search between properties
            - **Aliases**: [`var`, `v`]
            - **Type**: String
            - **Required**: **true**

5. `now_date`:
    - Output: The current date following the pattern given (if specified). 
    - Parameters:
        - `pattern`:
            - **Description**: Specify pattern that the date should follow.
            - **Aliases**: [`format`, `p`]
            - **Type**: String
            - **Default**: yyyy-MM-dd
            - **Required**: **false**

6. `now_time`:
    - Output: The current time following the pattern given (if specified). 
    - Parameters:
        - `pattern`:
            - **Description**: Specify pattern that the time should follow.
            - **Aliases**: [`format`, `p`]
            - **Type**: String
            - **Default**: HH:mm:ss
            - **Required**: **false**


### Conditions

### Functions























