/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.empresa.dao;

import java.util.List;
import pe.com.empresa.modelo.Docente;


public interface DocenteDao {
    public List listar();
    public Docente buscar(int iddocente);
    public boolean agregar(Docente docente);
    public boolean editar(Docente docente);
    public boolean eliminar(int iddocente); 
    
}
