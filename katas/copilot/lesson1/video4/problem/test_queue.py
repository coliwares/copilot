def test_queue():
    # Create a new queue
    queue = Queue()

    # Test is_empty() on an empty queue
    assert queue.is_empty() == True

    # Test enqueue() and size() on an empty queue
    queue.enqueue(1)
    assert queue.size() == 1

    # Test enqueue() and size() on a non-empty queue
    queue.enqueue(2)
    queue.enqueue(3)
    assert queue.size() == 3

    # Test dequeue() on a non-empty queue
    assert queue.dequeue() == 1
    assert queue.size() == 2

    # Test dequeue() on an empty queue
    assert queue.dequeue() == 2
    assert queue.dequeue() == 3
    assert queue.dequeue() == None
    assert queue.size() == 0

    # Test is_empty() on an empty queue
    assert queue.is_empty() == True

    print("All tests passed!")

test_queue()