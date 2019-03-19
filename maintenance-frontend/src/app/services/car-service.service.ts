import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CarDTO} from '../models/Car';

@Injectable({
  providedIn: 'root'
})
export class CarServiceService {

  constructor(private http: HttpClient) { }

  public getAllCars(): Observable<CarDTO[]> {
    return this.http.get<CarDTO[]>('http://localhost:8080/getAll');
  }

  public persistCar(car: CarDTO): Observable<string> {
    const httpOptions = {responseType: 'text' as 'json'};

    return this.http.put<string>('http://localhost:8080/persist', car, httpOptions);
  }
}
