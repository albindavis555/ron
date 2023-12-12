import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient} from '@angular/common/http'
@Injectable({
  providedIn: 'root'
})
export class HttpService {
  constructor(private http : HttpClient) { }
  public createcard(url : string,obj:any):Observable<Object>{

    console.log(this.http.post(url,obj))
    return this.http.post(url,obj);
  }
  public add<T>(url: string, par: any): Observable<T> {
    return this.http.post<T>(url, par);
}
public getById<T>(url: string, id: any, number: any): Observable<T> {
  return this.http.get<T>(url + id + "/" +number);
}
public verifyByDto<T>(url: string, Dto: any): Observable<T> {
  return this.http.post<T>( url  , Dto);
}
public deleteByCompositeId<T>(url: string, id1: any, id2: any): Observable<T> {
  return this.http.delete<T>( url + id1 + '/' + id2);
}
}