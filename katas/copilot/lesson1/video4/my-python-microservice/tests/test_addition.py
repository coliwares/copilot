import unittest
from src.addition import add

class TestAddition(unittest.TestCase):
    def test_add_positive_numbers(self):
        result = add(2, 3)
        self.assertEqual(result, 5)

    def test_add_negative_numbers(self):
        result = add(-5, -3)
        self.assertEqual(result, -8)

    def test_add_zero(self):
        result = add(0, 10)
        self.assertEqual(result, 10)

if __name__ == '__main__':
    unittest.main()