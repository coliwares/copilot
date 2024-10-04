def polish_notation_without_stack(expression):
    operators = {'+', '-', '*', '/'}
    expression = expression.split()
    for i in range(len(expression)):
        if expression[i] in operators:
            expression[i] = f'{expression[i]} {expression[i + 1]} {expression[i + 2]}'
            expression.pop(i + 1)
            expression.pop(i + 1)
            break
    return expression[0]