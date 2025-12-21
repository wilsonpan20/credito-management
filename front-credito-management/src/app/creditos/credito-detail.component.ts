import { Component, ChangeDetectionStrategy, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { map, switchMap, of } from 'rxjs';
import { CreditoService } from '../services/credito.service';
import { Credito } from '../models/credito.model';

@Component({
  selector: 'app-credito-detail',
  standalone: true,
  imports: [CommonModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <main class="credito-detail p-6 bg-gray-50 min-h-screen" aria-labelledby="credito-title">
      <h1 id="credito-title" class="text-4xl md:text-5xl font-extrabold text-gray-800 mb-6">Detalhe do Crédito</h1>

      <div *ngIf="(credito$ | async) as credito; else loading">
        <section *ngIf="credito; else notFound" class="card bg-white shadow-lg rounded-lg p-6">
          <table class="w-full table-auto border-collapse">
            <tbody>
              <tr class="border-b border-gray-200">
                <td class="py-4 px-5 font-semibold text-lg text-gray-700 bg-gray-100">Número do Crédito</td>
                <td class="py-4 px-5 text-base text-gray-900">{{ credito.numeroCredito }}</td>
              </tr>
              <tr class="border-b border-gray-200">
                <td class="py-3 px-4 font-semibold text-gray-700 bg-gray-100">Número NFS-e</td>
                <td class="py-3 px-4 text-gray-900">{{ credito.numeroNfse }}</td>
              </tr>
              <tr class="border-b border-gray-200">
                <td class="py-3 px-4 font-semibold text-gray-700 bg-gray-100">Data de Constituição</td>
                <td class="py-3 px-4 text-gray-900">{{ credito.dataConstituicao | date:'longDate' }}</td>
              </tr>
              <tr class="border-b border-gray-200">
                <td class="py-3 px-4 font-semibold text-gray-700 bg-gray-100">Valor ISSQN</td>
                <td class="py-3 px-4 text-gray-900">{{ credito.valorIssqn | number:'1.2-2' }}</td>
              </tr>
              <tr class="border-b border-gray-200">
                <td class="py-3 px-4 font-semibold text-gray-700 bg-gray-100">Tipo</td>
                <td class="py-3 px-4 text-gray-900">{{ credito.tipoCredito }}</td>
              </tr>
              <tr class="border-b border-gray-200">
                <td class="py-3 px-4 font-semibold text-gray-700 bg-gray-100">Simples Nacional</td>
                <td class="py-3 px-4 text-gray-900">{{ credito.simplesNacional ? 'Sim' : 'Não' }}</td>
              </tr>
              <tr class="border-b border-gray-200">
                <td class="py-3 px-4 font-semibold text-gray-700 bg-gray-100">Alíquota (%)</td>
                <td class="py-3 px-4 text-gray-900">{{ credito.aliquota | number:'1.2-2' }}</td>
              </tr>
              <tr class="border-b border-gray-200">
                <td class="py-3 px-4 font-semibold text-gray-700 bg-gray-100">Valor Faturado</td>
                <td class="py-3 px-4 text-gray-900">{{ credito.valorFaturado | number:'1.2-2' }}</td>
              </tr>
              <tr class="border-b border-gray-200">
                <td class="py-3 px-4 font-semibold text-gray-700 bg-gray-100">Valor Dedução</td>
                <td class="py-3 px-4 text-gray-900">{{ credito.valorDeducao | number:'1.2-2' }}</td>
              </tr>
              <tr>
                <td class="py-3 px-4 font-semibold text-gray-700 bg-gray-100">Base de Cálculo</td>
                <td class="py-3 px-4 text-gray-900">{{ credito.baseCalculo | number:'1.2-2' }}</td>
              </tr>
            </tbody>
          </table>

          <div class="actions mt-6 flex justify-end">
            <button type="button" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" (click)="router.navigate(['/creditos'])">Voltar</button>
          </div>
        </section>
      </div>

      <ng-template #notFound>
        <p class="text-gray-600">Crédito não encontrado.</p>
      </ng-template>

      <ng-template #loading>
        <p class="text-gray-600">Carregando...</p>
      </ng-template>
    </main>
  `
})
export class CreditoDetailComponent {
  private route = inject(ActivatedRoute);
  router = inject(Router);
  private service = inject(CreditoService);

  credito$ = this.route.paramMap.pipe(
    map(pm => pm.get('numeroCredito')),
    switchMap(numero =>
      numero ? this.service.findByNumeroCredito(numero) : of(null)
    )
  );
}