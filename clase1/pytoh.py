#nombre del producto
prod1 = input("Ingrese el nombre del primer producto: ")
prod2 = input("Ingrese el nombre del segundo producto: ")
prod3 = input("Ingrese el nombre del tecer producto: ")

print("""
Lista del mercado
=================
    Producto 1 : %s
    Producto 2 : %s
    Producto 3 : %s
""" %(prod1, prod2, prod3))


#cantidad del prodcuto
cant1 = float(input("cantidad del producto " + prod1 + ": "))
cant2 = float(input("cantidad del producto " + prod2 + ": "))
cant3 = float(input("cantidad del producto " + prod3 + ": "))

#Precio del producto
pre1 = float(input("Precio del producto " + prod1 + ": "))
pre2 = float(input("Precio del producto " + prod2 + ": "))
pre3 = float(input("Precio del producto " + prod3 + ": "))

#costo total del producto

costototal = cant1*pre1 + cant2*pre2 + cant3*pre3

#mostrar el costo total del producto
print("""
EL costo total es : %.3f
""" %(costototal))

