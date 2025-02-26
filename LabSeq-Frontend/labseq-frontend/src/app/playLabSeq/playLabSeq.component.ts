import { Component, ChangeDetectorRef, ViewEncapsulation, OnInit, Renderer2 } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-playLabSeq',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './playLabSeq.component.html',
  styleUrl: './playLabSeq.component.css',
  encapsulation: ViewEncapsulation.None
})
export class PlayLabSeqComponent {
  title = 'labseq-frontend';
  position: number = 0;
  labSeqvalue: any;
  labSequence: any;
  labSeqErrorValue: any;

  constructor(private router:Router, private http: HttpClient, private cdr: ChangeDetectorRef, private renderer: Renderer2){
    this.getAllLabSeq();
  }
  
  ngAfterViewInit(): void {
    this.cdr.detectChanges();
  }

  homePage(){
    this.router.navigate(['home']);
  }

  getAllLabSeq(){
    this.http.get(`/url/labseq/all`, { responseType: 'text' })
    .subscribe({
      next: (response) => {
        if (response && response.length > 0) {
          response = response.toString();
          response = response.replace("[", "");
          response = response.replace("]", "");
          this.labSequence = response + ", ...";
        }
      },
      error: (error) => {
        if(error.status == 401){
          this.labSeqErrorValue = "You are not authorized to view the resource";
        } else if(error.status == 403){
          this.labSeqErrorValue = "Accessing the resource you were trying to reach is forbidden";
        } else if(error.status == 404){
          this.labSeqErrorValue = "The resource you were trying to reach is not found";
        } else if(error.status == 500){
          this.labSeqErrorValue = "Application failed to process the request";
        } else {
          this.labSeqErrorValue = "Failed to fetch all LabSeq sequence";
        }
        const errorDiv = document.getElementById('error');
        if (errorDiv) {
          this.renderer.setStyle(errorDiv, 'display', 'block');
          this.renderer.addClass(document.getElementById('card1'), 'overlay');
          this.renderer.addClass(document.getElementById('card2'), 'overlay');
          this.position = 0;
          this.labSeqvalue = "";
        }
      }
    });
  }
  
  getValue() {
    this.http.get(`/url/labseq/${this.position}`, { responseType: 'text' })
      .subscribe({
        next: (response) => {
          this.labSeqvalue = response;
        },
        error: (error) => {
          if(error.status == 401){
            this.labSeqErrorValue = "You are not authorized to view the resource";
          } else if(error.status == 403){
            this.labSeqErrorValue = "Accessing the resource you were trying to reach is forbidden";
          } else if(error.status == 404){
            this.labSeqErrorValue = "The resource you were trying to reach is not found";
          } else if(error.status == 500){
            this.labSeqErrorValue = "Application failed to process the request";
          } else {
            this.labSeqErrorValue = "Failed to fetch LabSeq value";
          }
          const errorDiv = document.getElementById('error');
          if (errorDiv) {
            this.renderer.setStyle(errorDiv, 'display', 'block');
            this.renderer.addClass(document.getElementById('card1'), 'overlay');
            this.renderer.addClass(document.getElementById('card2'), 'overlay');
            this.position = 0;
            this.labSeqvalue = "";
          }
        }
      });
  }

  closeDiv(){
    const errorDiv = document.getElementById('error');
    if (errorDiv) {
      this.renderer.setStyle(errorDiv, 'display', 'none');
      this.renderer.removeClass(document.getElementById('card1'), 'overlay');
      this.renderer.removeClass(document.getElementById('card2'), 'overlay');
    }
  }

}