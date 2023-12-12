import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { rent } from '../model/rent.model';
import { RentService } from '../service/rent-service.service';


declare var $: any;
declare var jQuery: any;
@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',        
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
   
   validationMessage: any;
   pObj: rent = new rent();
   currentTabbedMenu: any = [];
   currentTab: boolean = false;

   name:any;
   id:any;
  constructor(private RentService: RentService, private router: Router,
    private route: ActivatedRoute) {
      this.id = (this.route.snapshot.paramMap.get('id1'));
      this.name = (this.route.snapshot.paramMap.get('id2'));
    }
  ngOnInit(): void {
    this.validationMessage = {};
    this.getUser();
  }
  getUser() {
    setTimeout(() => {
      this.RentService.GetUser(this.id,this.name).subscribe((item: any) => {
        this.pObj = item;

      $("#id").val(this.id).trigger('change'); 
      $("#name").val(this.name).trigger('change');
      $("#address").val(this.pObj.address).trigger('change');
      $("#mobileNo").val(this.pObj.mobileNo).trigger('change');
      $("#itemName").val(this.pObj.itemName).trigger('change');
      $("#quantity").val(this.pObj.quantity).trigger('change');
      $("#amount").val(this.pObj.amount).trigger('change');
      $("#deliverdDate").val(this.pObj.deliverdDate).trigger('change');
      $("#returnDate").val(this.pObj.returnDate).trigger('change');
      $("#status").val(this.pObj.status).trigger('change');
      $("#nn").val(this.pObj.nn).trigger('change');


      }, error => {
        console.log("error")
      }, () => {
        
      });
    }, 300);
  } 

  nn: string = ''; 

  updateValue(event: any) {
    this.nn = event.target.checked ? 'spam' : ''; 
  }

  updateUser() {
    this.validationMessage = {};
    this.pObj.id = $("#id").val();
    this.pObj.name = $("#name").val();
    this.pObj.address = $("#address").val();
    this.pObj.mobileNo = $("#mobileNo").val();
    this.pObj.itemName = $("#itemName").val();
    this.pObj.quantity = $("#quantity").val();
    this.pObj.amount = $("#amount").val();
    this.pObj.deliverdDate = $("#deliverdDate").val();
    this.pObj.returnDate = $("#returnDate").val();
    this.pObj.status = $("#status").val();
    this.pObj.nn = this.nn;
   


    
    this.RentService.UpdateUser(this.pObj).subscribe((item: any) => {
      if (item.body.toLowerCase() === "success") {
        console.log(JSON.stringify(item));

        this.router.navigate(['./details']);
       
         this.RentService.ShowToasts('User', 'Update', item.message, 'Success');
        // this.savePageDataWithencryption();
      } else {
        item.details.forEach(element => {
            var key = Object.keys(element)[0];
            this.validationMessage[key] = element[key];
        });
        console.log(this.validationMessage);
        // this.RentService.ShowToasts('User', 'Update', item.message, 'Danger');
      }
    }, error => {
      if (error.status == "400") {
        let msg = "";
        error.error.details.forEach(element => {
          msg = msg + element + "<br>"
        });
        // this.RentService.ShowToasts('User', '', msg, 'Danger');
      }
    }, () => {
      console.log("finally")
    });
  }
  backToUser() {
    this.router.navigate(['./details']);
  }
  clear() {
    
    let name = this.pObj.name;
    this.pObj = new rent();
    this.pObj.name = name;
    $("#id").val("0");
    $("#name").val("0");
    $("#address").val("0");
    $("#mobileNo").val("0");
    $("#itemName").val("0").trigger('change');
    $("#quantity").val("0").trigger('change');
    $("#amount").val("0");
    $('#deliverdDate').val("");
    $("#returnDate").val("");
    $("#status").val("").trigger('change');
    $("#nn").val("");

    this.validationMessage={}

  }
}






