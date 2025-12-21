import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Credito } from '../models/credito.model';

@Injectable({ providedIn: 'root' })
export class CreditoService {
  private http = inject(HttpClient);
  private readonly baseUrl = 'http://localhost:8080/api/creditos'; 

  findByNumeroNfse(numeroNfse: string): Observable<Credito[]> {
    return this.http.get<Credito[]>(`${this.baseUrl}/${encodeURIComponent(numeroNfse)}`);
  }

  findByNumeroCredito(numeroCredito: string): Observable<Credito> {
    return this.http.get<Credito>(`${this.baseUrl}/credito/${encodeURIComponent(numeroCredito)}`);
  }
}
