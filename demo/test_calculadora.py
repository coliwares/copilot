from calculadora import suma


def test_suma():
    # Test case 1: Positive numbers
    assert suma(2, 3) == 5

    # Test case 2: Negative numbers
    assert suma(-5, -7) == -12

    # Test case 3: Zero
    assert suma(0, 0) == 0

    # Test case 4: Decimal numbers
    assert suma(1.5, 2.5) == 4.0

    # Test case 5: Large numbers
    assert suma(1000000, 2000000) == 3000000

test_suma()