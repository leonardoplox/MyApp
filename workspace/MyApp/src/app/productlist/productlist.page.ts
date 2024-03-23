import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { Product, ProductsService } from '../service/products.service';

@Component({
  selector: 'app-productlist',
  templateUrl: './productlist.page.html',
  styleUrls: ['./productlist.page.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule, FormsModule]
})
export class ProductlistPage implements OnInit {

  products: Product[]=[];

  productsService=inject(ProductsService)

 async ngOnInit() {
    const response = await this.productsService.getALL();
    this.products = response.results;
  }
  
}
