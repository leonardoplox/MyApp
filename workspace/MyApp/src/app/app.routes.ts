import { Routes } from '@angular/router';

export const routes: Routes = [
  {path: 'main', loadComponent:()=> import('./main/main.page').then(m=>m.MainPage) },
  {
    path: 'productlist',
    loadComponent: () => import('./productlist/productlist.page').then( m => m.ProductlistPage)
  },
  {
    path: 'videojuegos',
    loadComponent: () => import('./videojuegos/videojuegos.page').then( m => m.VideojuegosPage)
  }
];
