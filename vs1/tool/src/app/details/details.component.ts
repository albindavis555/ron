import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';


import 'jquery';
import { rent } from '../model/rent.model';
import { RentService } from '../service/rent-service.service';
// import { ReturnStatement } from '@angular/compiler';

declare var $: any;
declare var jQuery: any;

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {
  
  static obj: DetailsComponent;
  slect2: string = "";
  // username: string = "";
  
  pObj: rent = new rent();
  selectedUserIdForDelete: any;
  selectedUserIdForDelete1: any;
  selectedUserIdForDelete2: any;
  userDatatable: any;
  dataTableUrl: string;
  coloumDropdown: boolean = false;
  securityUser: any = {};
  initialHead: HeaderConfig[] = [
    { name: "Id", isSelected: false },
    { name: "Name", isSelected: false },
    { name: "Address", isSelected: false },
    { name: "Mobile No", isSelected: false },
    { name: "Item", isSelected: false },
    { name: "Quantity", isSelected:false},
    { name: "Amount", isSelected: false },
    { name: "Deliverd Date", isSelected: false },
    { name: "Return Date", isSelected: false },
    { name: "Status", isSelected: false },
  ];
  currentTabbedMenu: any = [];
  currentTab: boolean = false;
  statusCountList: StatusCountModel = new StatusCountModel();
  // toastr: any;
  
  constructor(private RentService: RentService, private router: Router, private toastr:ToastrService) {
    this.dataTableUrl = "http://localhost:8088/rent/search";
  }
  ngOnInit(): void {
    DetailsComponent.obj = this;
   this. getUsers();
  }
  refresh(){
    this.userDatatable.ajax.reload();
  }

   getUsers() {
  
    this.userDatatable = $('#userList').DataTable({
      "ordering": true,
      "bProcessing": true,
      "bDeferRender": true,
      bAutoWidth: false,
      bServerSide: true,
      sAjaxSource: this.dataTableUrl,
      "fnServerParams": function (aoData) {
        var dataString = DetailsComponent.obj.getSearchInputs();
        aoData.push({ name: "searchParam", value: dataString });
      },
      "order": [[0, 'asc']],

      "fnServerData": (sSource, aoData, fnCallback, oSettings) => {
        oSettings.jqXHR = $.ajax({
          "dataType": 'json',
          "type": "GET",
          "url": sSource,
          "data": aoData,
          "beforeSend": (xhr) => {
            // let token: any = this.RentService.getUserDetailsWithToken();
            // xhr.setRequestHeader("Authorization", "Bearer " + token.access_token)
          },
          "success": (data) => {
            // console.log(this.statusCountList)
            // console.log(data.countByType)
            this.RentService.setStatusCount(this.statusCountList, data.countByType);
            
            fnCallback(data);
          },
          
          "error": (e) => {
            if (e.status == "403" || e.status == "401") {
              // this.ParkingService.GoToErrorHandling(e);
            }
          }
        
        });
      },
     
      

       "sDom": "<rt><'row border-top pt-2'<'col-sm-12 col-md-5'l><'col-sm-12 col-md-7'p>>",
      "aoColumns": [
        { "mDataProp": "id", "bSortable": false },
        { "mDataProp": "name", "bSortable": false },
        { "mDataProp": "address", "bSortable": false },
        { "mDataProp": "mobileNo","bSortable": false},
        { "mDataProp": "itemName", "bSortable": false },
        { "mDataProp": "quantity", "bSortable": false },
        { "mDataProp": "amount", "bSortable":false},
        { "mDataProp": "deliverdDate", "bSortable":false},
        { "mDataProp": "returnDate", "bSortable":false},
        { "mDataProp": "status", "bSortable":false,

          "mRender": function (data) {
         
          if (data == 'PROCESSD') {
            return '<span class="badge badge-primary ipsh-badge-pending">Pending</span>';
           } 
          //  else if (data == 'DELETE') {
          //  return '<span class="badge badge-primary ipsh-badge-pending">Pending Appro</span>';
          // } 
          else if (data == 'VERIFIED') {
            return '<span class="badge badge-primary ipsh-badge-approve">Approved</span>'
          } else if (data == 'VERIFYDELETE') {
            return '<span class="badge badge-primary ipsh-badge-reject">Verify Delete</span>'
          } else if (data == 'RETURNED') {
            return '<span class="badge badge-danger ipsh-badge-returned">RETURNED</span>'
          } else if (data == 'RECEIVED') {
            return '<span class="badge badge-primary ipsh-badge-approve">Processed</span>'
          }
          return data;
          
        }}
      ],
      "fnRowCallback": function (nRow, aData) {
        // Get today's date as a string in the format "YYYY-MM-DD"
        const today = new Date();
        const todayDateStr = today.toISOString().split('T')[0];

        if (aData.nn === 'spam') {
          $(nRow).css("background-color", "#cc0000");
        }
        else if (aData.returnDate === todayDateStr) {
          $(nRow).css("background-color", "#009999");
        }
        else if (aData.mobileNo === '12345') {
          $(nRow).css("background-color", "#a7a7a7");
        }
        }
    });    
         
      $('#userList tbody').on('click', 'tr', (event) => {
     
      $(event.currentTarget).toggleClass('selected');
    });
    
  }

  addColumn() {
    this.coloumDropdown = !this.coloumDropdown;
  }
  
  getSearchInputs() {
    let pSearch: rent = new rent();
    pSearch.name = $('#name').val();
    pSearch.mobileNo = $('#mobileNo').val();
    pSearch.deliverdDate = $('#deliverdDate').val();
    pSearch.returnDate = $('#returnDate').val();
    pSearch.itemName = $('#itemName').val();
    pSearch.status = $('#status').val();
    
    if (pSearch.name == null || pSearch.name == undefined) {
      pSearch.name = null;
    }
    if (pSearch.mobileNo == null || pSearch.mobileNo == undefined) {
      pSearch.mobileNo = null;
    }
    if (pSearch.deliverdDate == null || pSearch.deliverdDate == undefined) {
      pSearch.deliverdDate = null;
    }
    if (pSearch.returnDate == null || pSearch.returnDate == undefined) {
      pSearch.returnDate= null;
    }
    if (pSearch.itemName == null || pSearch.itemName == undefined) {
      pSearch.itemName= null;
    }
    if (pSearch.status == null || pSearch.status == undefined) {
      pSearch.status= null;
    }

    if (Object.values(pSearch).length>0) {
      return JSON.stringify(pSearch);
    }
    return "";
}
  showHideColoum(values: any, data) {
    var newOrder = this.userDatatable.colReorder.order();
    var col = parseInt(values.currentTarget.value);
    var index = jQuery.inArray(col, newOrder);
    if (data.isSelected == true) {
      this.userDatatable.column(index).visible(true);
    } else {
      this.userDatatable.column(index).visible(false);
    }
  }
  refreshByStatus(status: string) {
    $("#status").val(status).trigger("change");
    this.search();
  }
  search() {
    // this.slect2 = $("#id-userType").val();
    this.userDatatable.draw();
  }
  clear() {

    $('#name').val('');
    $('#address').val('');
    $('#mobileNo').val('')
    $('#itemName').val('').trigger("change");
    $('#quantity').val('');
    $('#name').val('');
    $('#deliverdDate').val('');
    $('#returnDate').val('');
    $('#status').val('').trigger("change");
    this.userDatatable.draw();
    
  }
  get(label) {
    return  label;
  }
  createUser() {
    this.router.navigate(['/create']);
  }
  updateUser() {
    if (this.userDatatable.rows('.selected').data().length == 0) {
        this.toastr.warning("Please Select a Record To Update.", 'Warning',{ positionClass: 'toast-top-right' });
    } else if (this.userDatatable.rows('.selected').data().length > 1) {
        this.toastr.warning("Multiple Record You Cannot Update", 'Warning',{ positionClass: 'toast-top-right' });
      
    } else
        console.log(this.pObj)
        console.log(this.userDatatable.rows('.selected').data()[0].userType);
        let selectedIdForUpdate1 = this.userDatatable.rows('.selected').data()[0].id;
        let selectedIdForUpdate2 = this.userDatatable.rows('.selected').data()[0].name;
        console.log("details"+selectedIdForUpdate2);
        this.router.navigate([`./update/${selectedIdForUpdate1}/${selectedIdForUpdate2}`]);
      }
      Verify() {
        // if (this.userDatatable.rows('.selected').data().length == 0) {
        //   this.RentService.ShowToasts('', '', "Please Select a Record To Verify.", 'Warning');
        // } else if (this.userDatatable.rows('.selected').data().length > 1) {
        //     this.RentService.ShowToasts('', '', "Multiple Record Cannot Verify.", 'Warning');
        // } else {
        //     var status: any;
        //     var name: any;
        //     var mobileNo: any;
        //    status = this.userDatatable.rows('.selected').data()[0].status;
        //    name = this.userDatatable.rows('.selected').data()[0].name;
        //    mobileNo = this.userDatatable.rows('.selected').data()[0].mobileNo;
        //     if (status == "VERIFIED") {
        //       this.RentService.ShowToasts('User Details', 'Verify', 'User Details Already Verified !', 'Info');
        //     } else {
        //       let selectedIdForVerify1  = window.btoa(name);
        //       let selectedIdForVerify2  = window.btoa(mobileNo);
        //       this.router.navigate(['./verify', selectedIdForVerify1,selectedIdForVerify2]);
        //     }
        // }
      }
      // deleteUser() {
      //   if (this.userDatatable.rows('.selected').data().length == 0) {
      //     this.RentService.ShowToasts('', '', "Please Select a Record To Delete.", 'Warning');
      //     this.toastr.info("Please select a record to delete.", 'User', { positionClass: 'toast-bottom-right' });
      //   } else if (this.userDatatable.rows('.selected').data().length > 1) {
      //       this.RentService.ShowToasts('', '', "Multiple Record You Cannot Delete.", 'Warning');
      //       this.toastr.warning("Multiple record you can not delete.", 'User', { positionClass: 'toast-bottom-right' });
      //   } else {
      //     this.selectedUserIdForDelete1 = this.userDatatable.rows('.selected').data()[0].name;
      //     this.selectedUserIdForDelete2 = this.userDatatable.rows('.selected').data()[0].mobileNo;
      //     this.refresh();
      //     $("#delete-user-modal").modal("show");
      //   }
      // }
      
      confirmDeleteUser() {
        if (this.userDatatable.rows('.selected').data().length == 0) {

          this.toastr.warning('Record not selected!', 'warning!',{ positionClass: 'toast-top-right' });
        } else if (this.userDatatable.rows('.selected').data().length > 1) {

           this.toastr.warning("Multiple record you can not returned.", 'User', { positionClass: 'toast-top-right' });
        } else {
          this.selectedUserIdForDelete1 = this.userDatatable.rows('.selected').data()[0].id;
          this.selectedUserIdForDelete2 = this.userDatatable.rows('.selected').data()[0].name;
          this.refresh();
          
          this.toastr.success("Returned", 'User', { positionClass: 'toast-top-right' });
          // window.location.reload();
          setTimeout(function() {
            // alert('Reloading the page...');
            this.location.reload();
        },2000);
        }
          
          this.RentService.DeleteUser(this.selectedUserIdForDelete1,this.selectedUserIdForDelete2).subscribe((item: any) => {
          this.selectedUserIdForDelete1 = "";

          
          this.selectedUserIdForDelete2 = "";
          $("#delete-user-modal").modal("hide");
          console.log(item);
          this.RentService.ShowToasts('User', 'Sub', item.message, 'Success');

          this.userDatatable.draw();
          this.toastr.success(item.message, 'User', { positionClass: 'toast-top-right' });
        }, error => {
          console.log("error")
        }, () => {

          console.log("finally")
        });
      }

    }
export class HeaderConfig {
  name: string;
  isSelected: boolean;
}
export class StatusCountModel {
  delete: number;
  deleted: number;
  returned: number;
  processd: number;
  totalStatus: number;

  constructor() {
    this.delete = 0;
    this.deleted = 0;
    this.returned = 0;
    this.processd = 0;
    this.totalStatus = 0;
    
  }
}