import { Component, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  encapsulation: ViewEncapsulation.None
})
export class HomeComponent {
  title = 'labseq-frontend';

  constructor(private router:Router){}

  play(){
    this.router.navigate(['playLabSeq']);
  }
}