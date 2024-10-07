## genera una calculadora con las operaciones basicas
## de suma, resta, multiplicacion y division

def suma(a, b):
    return a + b

def resta(a, b):
    return a - b

def multiplicacion(a, b):
    return a * b

def division(a, b):
    return a / b


def calculadora():
    print("Calculadora")
    print("1. Suma")
    print("2. Resta")
    print("3. Multiplicacion")
    print("4. Division")
    print("5. Salir")
    opcion = int(input("Opcion: "))
    if opcion == 5:
        return
    a = float(input("a: "))
    b = float(input("b: "))
    if opcion == 1:
        print(suma(a, b))
    elif opcion == 2:
        print(resta(a, b))
    elif opcion == 3:
        print(multiplicacion(a, b))
    elif opcion == 4:
        print(division(a, b))
    else:
        print("Opcion invalida")



calculadora()


