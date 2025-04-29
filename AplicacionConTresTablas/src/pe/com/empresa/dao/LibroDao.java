/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.empresa.dao;

import java.util.List;
import pe.com.empresa.modelo.Libro;

/**
 *
 * @author pepon
 */
public interface LibroDao {
     public List listar();
    public Libro buscar(int idlibro);
    public boolean agregar(Libro libro);
    public boolean editar(Libro libro);
    public boolean eliminar(int idlibro); 
}
