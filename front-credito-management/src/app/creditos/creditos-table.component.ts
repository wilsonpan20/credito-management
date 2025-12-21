import { Component, ChangeDetectionStrategy, input, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import type { Credito } from '../models/credito.model';

@Component({
  selector: 'app-creditos-table',
  standalone: true,
  imports: [CommonModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <section class="results-section">
      <div *ngIf="creditosArray.length > 0; else noResults" class="table-wrap" role="region" aria-label="Resultados da busca">
        <table class="credit-table" role="table">
          <caption class="visually-hidden">Lista de créditos encontrados</caption>
          <thead>
            <tr>
              <th scope="col" class="col-id"># Crédito</th>
              <th scope="col" class="col-nfse">NFS-e</th>
              <th scope="col" class="col-date">Data</th>
              <th scope="col" class="col-num">Valor ISSQN</th>
              <th scope="col">Tipo</th>
              <th scope="col">Simples</th>
              <th scope="col" class="col-num">Alíquota</th>
              <th scope="col" class="col-num">Faturado</th>
              <th scope="col" class="col-num">Dedução</th>
              <th scope="col" class="col-num">Base</th>
            </tr>
          </thead>
          <tbody>
            <tr *ngFor="let c of creditosArray; trackBy: trackByNumeroCredito" tabindex="0" (click)="onSelect(c)" (keydown.enter)="onSelect(c)" role="row" aria-label="Crédito {{c.numeroCredito}}">
              <td class="col-id">{{ c.numeroCredito }}</td>
              <td class="col-nfse">{{ c.numeroNfse }}</td>
              <td class="col-date">{{ c.dataConstituicao | date:'mediumDate' }}</td>
              <td class="col-num">{{ c.valorIssqn | number:'1.2-2' }}</td>
              <td>{{ c.tipoCredito }}</td>
              <td>{{ c.simplesNacional ? 'Sim' : 'Não' }}</td>
              <td class="col-num">{{ c.aliquota | number:'1.2-2' }}%</td>
              <td class="col-num">{{ c.valorFaturado | number:'1.2-2' }}</td>
              <td class="col-num">{{ c.valorDeducao | number:'1.2-2' }}</td>
              <td class="col-num">{{ c.baseCalculo | number:'1.2-2' }}</td>
            </tr>
          </tbody>
        </table>
      </div>

      <ng-template #noResults>
        <p class="no-results">Nenhum crédito encontrado.</p>
      </ng-template>
    </section>
  `,
  styles: [
    `
      :host { display:block }
      .table-wrap { border-radius:8px; overflow:auto; background:#fff; max-height:60vh; border:1px solid #d1d5db }

      /* Clear, visible borders for table and cells (higher contrast for accessibility) */
      .credit-table { width:100%; border-collapse:collapse; font-size:0.95rem; border:1px solid #d1d5db }
      .credit-table thead th { position:sticky; top:0; background:#fafafa; padding:0.85rem; text-align:left; font-weight:700; border-bottom:1px solid #eaecef; border-right:1px solid #d1d5db; z-index:2 }
      .credit-table tbody td { padding:0.75rem; border-bottom:1px solid #e6edf0; border-right:1px solid #d1d5db; vertical-align:middle }

      /* remove right border on the last column to avoid double border at the edge */
      .credit-table thead th:last-child,
      .credit-table tbody td:last-child { border-right: none }

      /* remove bottom border from last row for a cleaner look */
      .credit-table tbody tr:last-child td { border-bottom: none }

      .credit-table tbody tr { transition:background .12s ease, transform .08s ease }
      .credit-table tbody tr:hover { background: #fbfdff; cursor:pointer }
      .credit-table tbody tr:focus { outline:3px solid rgba(37,99,235,0.18); outline-offset:-3px }
      .credit-table .col-num { text-align:right; white-space:nowrap }
      .credit-table .col-date { text-align:left; white-space:nowrap; color:#374151 }
      .credit-table .col-id { width:8%; font-weight:600 }
      .credit-table .col-nfse { width:12% }
      .no-results { padding:0.75rem; color:#6b7280 }

      /* zebra rows for better scanning */
      .credit-table tbody tr:nth-child(even) td { background: #fff }

      @media (max-width: 680px) {
        .table-wrap { max-height:none }
        .credit-table thead { display:none }
        .credit-table tbody td { display:block; padding:0.6rem; position:relative }
        .credit-table tbody td::before { content:attr(data-label); display:block; font-weight:600; color:#374151; margin-bottom:0.25rem }
        .credit-table tbody tr { margin-bottom:0.5rem; border:1px solid #e6e6e6; border-radius:6px; overflow:hidden }
        .credit-table tbody td { border-right:none }
      }
    `,
  ],
})
export class CreditosTableComponent {
  creditos = input<Credito[]>();
  private router = inject(Router);

  get creditosArray(): Credito[] {
    return this.creditos?.() ?? [];
  }

  trackByNumeroCredito(_: number, item: Credito) {
    return item.numeroCredito;
  }

  onSelect(item: Credito) {
    // navigate to detail route when a row is clicked or activated
    this.router.navigate(['/creditos', item.numeroCredito]);
  }
}
