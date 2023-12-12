import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from './http-service.service';
declare var $: any;
@Injectable({
  providedIn: 'root'
})
export class RentService {
 
  constructor(private httpService : HttpService) { }
  savedToast: any = {};
  toastPosition = "";
  toastType = "";
  userInfo: any = {};
  customStompClient: any;
  localMessages: any = {};

  public CreateUser(userObj: any): Observable<any> {
    // console.log("service error")
    return Observable.create((observer) => {
      const apiUrl ="http://localhost:8088/rent/create";
      this.httpService.createcard(apiUrl,userObj)
            .subscribe((tokenData: any) => {
                observer.next(tokenData);
            }, (error) => {
                observer.error(error)
                console.log("error",error)
            },
                () => {
                    observer.complete()
                });
    });
}
public GetUser(id: any,name: String): Observable<any> {
  return Observable.create((observer) => {
      const apiUrl ="http://localhost:8088/rent/get/";
      this.httpService.getById<any>(apiUrl, id, name)
          .subscribe((tokenData: any) => {
              observer.next(tokenData);
          }, (error) => {
              // this.commonServiceProvider.GoToErrorHandling(error);
              observer.error(error)
          },
              () => {
                  observer.complete()
              });
  });
}
public UpdateUser(pObj: any): Observable<any> {
  return Observable.create((observer) => {
    const apiUrl ="http://localhost:8088/rent/update";
      this.httpService.add<any>(apiUrl, pObj)
          .subscribe((tokenData: any) => {
              observer.next(tokenData);
          }, (error) => {
             
              observer.error(error)
          },
              () => {
                  observer.complete()
              });
  });
}
public verifyUser(Obj: any): Observable<any> {
  return Observable.create((observer) => {
    const apiUrl ="http://localhost:8088/rent/verify";
    this.httpService.verifyByDto<any>(apiUrl, Obj)
      .subscribe((tokenData: any) => {
        observer.next(tokenData);
      }, (error) => {
        observer.error(error)
      },
        () => {
          observer.complete()
        });
  });
}
public DeleteUser(id: any, name: any): Observable<any> {
  return Observable.create((observer) => {
    const apiUrl ="http://localhost:8088/rent/delete/";
      this.httpService.deleteByCompositeId<any>(apiUrl, id,name)
          .subscribe((tokenData: any) => {
              observer.next(tokenData);
          }, (error) => {
              // this.commonServiceProvider.GoToErrorHandling(error);
              observer.error(error)
          },
              () => {
                  observer.complete()
              });
  });
}
public setStatusCount(statusCountList, data) {
  let total = 0;
  statusCountList.failed = 0;
  for (let index = 0; index < data.length; index++) {
      if (data[index].name == 'PROCESSD') {
          statusCountList.processd = data[index].count;
          total = total + data[index].count;
      }else if (data[index].name == 'RETURNED') {
          statusCountList.returned = data[index].count;
          total = total + data[index].count;
      }
      else if (data[index].name == 'PENDING PROCESS') {
          statusCountList.pendingReprocess = data[index].count;
          total = total + data[index].count;
      }
  }
  statusCountList.totalStatus = total;
}
ShowToasts(title: string, subtitle: string, message: string, type: string) {
let iconName = ""
//  let currentUserDetail = JSON.parse(localStorage.getItem("userDetails"));
//  let currentConfigDetail = JSON.parse(localStorage.getItem("themeConfig"));
if (type == "Success") {
    this.toastType = "bg-success";
    iconName = "fas fa-info-circle";
} else if (type == "Info") {
    this.toastType = "bg-info";
    iconName = "fas fa-info-circle";
} else if (type == "Warning") {
    this.toastType = "bg-warning";
    iconName = "fas fa-exclamation-circle";
}
else if (type == "Danger") {
    this.toastType = "bg-danger";
    iconName = "fas fa-exclamation-triangle";
}
if (this.savedToast.position != undefined) {
    if (this.savedToast.position == "TL") {
        this.toastPosition = "topLeft";
    } else if (this.savedToast.position == "TR") {
        this.toastPosition = "topRight";
    } else if (this.savedToast.position == "BL") {
        this.toastPosition = "bottomLeft";
    }
    else if (this.savedToast.position == "BR") {
        this.toastPosition = "bottomRight";
    }
    else if (this.savedToast.position == "TC") {
        this.toastPosition = "topCenter";
    }
    else if (this.savedToast.position == "BC") {
        this.toastPosition = "bottomCenter";
    }
} else {
    this.toastPosition = "topCenter";
}
// $(document).Toasts('create', {
//     class: this.toastType,
//     title: message,
//     subtitle: '',
//     position: this.toastPosition,
//     icon: iconName,
//     autohide: true,
//     delay: 5000,
// }
// )
return 1;
}
}