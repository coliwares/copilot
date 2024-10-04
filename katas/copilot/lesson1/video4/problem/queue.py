from typing import Any, List

class Queue:
    """A class representing a queue data structure."""

    def __init__(self) -> None:
        self.items: List[Any] = []

    def is_empty(self) -> bool:
        """Check if the queue is empty.

        Returns:
            bool: True if the queue is empty, False otherwise.
        """
        return len(self.items) == 0

    def enqueue(self, item: Any) -> None:
        """Add an item to the end of the queue.

        Args:
            item (Any): The item to be added to the queue.
        """
        self.items.append(item)

    def dequeue(self) -> Any:
        """Remove and return the first item from the queue.

        Returns:
            Any: The first item in the queue, or None if the queue is empty.
        """
        if self.is_empty():
            return None
        return self.items.pop(0)

    def size(self) -> int:
        """Get the number of items in the queue.

        Returns:
            int: The number of items in the queue.
        """
        return len(self.items)