import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { rent } from '../model/rent.model';
import { RentService } from '../service/rent-service.service';
import { ToastrService } from 'ngx-toastr';

declare var $: any;
declare var jQuery: any;

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  
  userObj: rent = new rent();
  validationMessage: any;
  nn: string = '';
  constructor(private RentService:RentService, private router: Router, private toastr:ToastrService) {
  }

  ngOnInit() {
    this.validationMessage = {};
  }
  
  updateValue(event: any) {
    this.nn = event.target.checked ? 'spam' : ''; 
  }

  addUser() {
    
    this.validationMessage = {};
    this.userObj.name = $("#name").val() ;
    this.userObj.address = $("#address").val();
    this.userObj.mobileNo = $("#mobileNo").val();
    this.userObj.itemName = $("#itemName").val();
    this.userObj.quantity = $("#quantity").val();
    this.userObj.amount = $("#amount").val();
    this.userObj.deliverdDate = $("#deliverdDate").val();
    this.userObj.returnDate = $("#returnDate").val();
    this.userObj.nn = this.nn;
  
    this.RentService.CreateUser(this.userObj).subscribe((item: any) => {
    
      if (item.code.toLowerCase() == "success") {
        this.router.navigate(['./details']);
        this.clear();
        this.toastr.success("Customer added", 'success', { positionClass: 'toast-top-center' });
      }else {
            
        if (item.details) {
        
          item.details.forEach(element => {
            var key = Object.keys(element)[0];
            this.validationMessage[key] = element[key];
            
          });
        console.log(this.validationMessage);
        }
      }
    },);
    
    console.log(this.validationMessage);
  }

  backToUser() {
    this.router.navigate(['./details']);
  }

  clear() {

    $("#name").val('');
    $('#address').val('');
    $("#mobileNo").val('');
    $("#itemName").val('').trigger('change');
    $("#quantity").val('');
    $("#amount").val("");
    $("#deliverdDate").val('');
    $("#returnDate").val('');
    $("#nn").val('');
    this.validationMessage={}
  }
}