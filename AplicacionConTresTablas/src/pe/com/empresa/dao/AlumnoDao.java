/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.empresa.dao;

import java.util.List;
import pe.com.empresa.modelo.Alumno;
/**
 *
 * @author pepon
 */
public interface AlumnoDao {
    public List listar();
    public Alumno buscar(int idalumno);
    public boolean agregar(Alumno alumno);
    public boolean editar(Alumno alumno);
    public boolean eliminar(int idalumno);
    
}
