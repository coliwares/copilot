# My Python Microservice

This is a microservice written in Python that provides addition and subtraction functionality.

## Project Structure

```
my-python-microservice
├── src
│   ├── addition.py
│   ├── subtraction.py
│   └── main.py
├── tests
│   ├── test_addition.py
│   └── test_subtraction.py
├── .gitignore
├── requirements.txt
└── README.md
```

## Files

### `src/addition.py`

This file contains the implementation of the addition functionality. It exports a function `add` that takes two numbers as input and returns their sum.

### `src/subtraction.py`

This file contains the implementation of the subtraction functionality. It exports a function `subtract` that takes two numbers as input and returns their difference.

### `src/main.py`

This file is the entry point of the microservice. It imports the `add` and `subtract` functions from the `addition.py` and `subtraction.py` files respectively. It sets up a Flask server and defines the routes for the addition and subtraction operations.

### `tests/test_addition.py`

This file contains the unit tests for the addition functionality. It imports the `add` function from the `addition.py` file and tests its behavior with different input scenarios.

### `tests/test_subtraction.py`

This file contains the unit tests for the subtraction functionality. It imports the `subtract` function from the `subtraction.py` file and tests its behavior with different input scenarios.

### `.gitignore`

This file specifies the files and directories that should be ignored by Git version control.

### `requirements.txt`

This file lists the Python dependencies required for the project. It specifies the packages and their versions that need to be installed.

## Setup

1. Clone the repository.
2. Install the required dependencies by running `pip install -r requirements.txt`.
3. Run the microservice by executing `python src/main.py`.
4. The microservice will be accessible at `http://localhost:5000`.

## Usage

### Addition

To perform an addition operation, send a POST request to `http://localhost:5000/add` with the following JSON payload:

```json
{
  "num1": 5,
  "num2": 3
}
```

The response will be a JSON object containing the sum of the two numbers:

```json
{
  "result": 8
}
```

### Subtraction

To perform a subtraction operation, send a POST request to `http://localhost:5000/subtract` with the following JSON payload:

```json
{
  "num1": 5,
  "num2": 3
}
```

The response will be a JSON object containing the difference between the two numbers:

```json
{
  "result": 2
}
```

## Testing

To run the unit tests, execute the following command:

```bash
python -m unittest discover tests
```

## Contributing

Contributions are welcome! If you have any suggestions or improvements, please open an issue or submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.