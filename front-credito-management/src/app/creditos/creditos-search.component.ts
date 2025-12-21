import { Component, ChangeDetectionStrategy, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { firstValueFrom } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { CreditoService } from '../services/credito.service';
import { CreditosTableComponent } from './creditos-table.component';
import type { Credito } from '../models/credito.model';

@Component({
  selector: 'app-creditos-search',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, CreditosTableComponent],
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <section
      class="flex items-center justify-center min-h-[60vh] p-6 bg-gray-50"
      aria-labelledby="search-heading"
    >
      <div
        class="w-full max-w-full bg-white rounded-xl shadow-lg p-6 sm:p-4 relative"
        role="region"
        [attr.aria-busy]="loading() ? 'true' : null"
      >
        <h2 id="search-heading" class="text-2xl md:text-3xl font-extrabold text-slate-900 mb-2">
          Consulta de Créditos
        </h2>
        <p class="text-sm text-slate-500 mb-4">Pesquise por NFS-e ou número do crédito. <span class="font-medium">Preencha apenas um dos campos.</span></p>

        <form class="w-full" [formGroup]="form" (ngSubmit)="onSubmit()" novalidate>
          <div class="grid grid-cols-1 sm:grid-cols-2 gap-4 items-end">
            <div>
              <label for="nfse" class="block text-sm font-semibold text-gray-700">
                Número NFS-e
              </label>
              <input
                id="nfse"
                type="text"
                formControlName="numeroNfse"
                class="mt-1 block w-full rounded-lg border border-gray-200 px-4 py-3 sm:px-3 sm:py-2 sm:text-sm shadow-sm text-base focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 transition"
                placeholder="Ex: 123456"
                aria-describedby="nfse-help"
              />
              <p id="nfse-help" class="text-xs text-gray-400 mt-1">Opcional — preencha apenas um dos campos</p>
            </div>

            <div>
              <label for="credito" class="block text-sm font-semibold text-gray-700">
                Número do Crédito
              </label>
              <input
                id="credito"
                type="text"
                formControlName="numeroCredito"
                class="mt-1 block w-full rounded-lg border border-gray-200 px-4 py-3 sm:px-3 sm:py-2 sm:text-sm shadow-sm text-base focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 transition"
                placeholder="Ex: 1000"
                aria-describedby="credito-help"
              />
              <p id="credito-help" class="text-xs text-gray-400 mt-1">Opcional — preencha apenas um dos campos</p>
            </div>
          </div>

          <div class="mt-6 flex justify-end gap-3">
            <button
              type="submit"
              class="inline-flex items-center gap-2 px-5 py-3 sm:px-3 sm:py-2 sm:text-sm rounded-lg bg-indigo-600 text-white font-semibold shadow hover:bg-indigo-700 focus:ring-2 focus:ring-indigo-500 transition"
              [disabled]="loading()"
            >
              <svg
                *ngIf="loading()"
                class="animate-spin h-5 w-5 text-white"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
              >
                <circle
                  class="opacity-25"
                  cx="12"
                  cy="12"
                  r="10"
                  stroke="currentColor"
                  stroke-width="4"
                ></circle>
                <path
                  class="opacity-75"
                  fill="currentColor"
                  d="M4 12a8 8 0 018-8v4a4 4 0 00-4 4H4z"
                ></path>
              </svg>
              <span *ngIf="!loading()">Buscar</span>
              <span *ngIf="loading()" class="sr-only">Buscando…</span>
            </button>

            <button
              type="button"
              class="inline-flex items-center gap-2 px-4 py-3 sm:px-3 sm:py-2 sm:text-sm rounded-lg border border-gray-300 bg-white hover:bg-gray-50 transition"
              (click)="clear()"
              [disabled]="loading()"
            >
              Limpar
            </button>
          </div>

          <p class="text-sm text-gray-500 mt-3">Preencha apenas um dos campos para efetuar a busca.</p>
          <p *ngIf="error()" class="text-sm text-red-600 mt-2" role="alert" aria-live="assertive">
            {{ error() }}
          </p>
        </form>

        <div class="mt-6 results-area w-full" tabindex="-1">
          <app-creditos-table [creditos]="creditos()"></app-creditos-table>
        </div>

        <div
          *ngIf="loading()"
          class="absolute inset-0 bg-white/70 rounded-xl flex items-center justify-center"
        >
          <svg
            class="animate-spin h-8 w-8 text-indigo-600"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
          >
            <circle
              class="opacity-25"
              cx="12"
              cy="12"
              r="10"
              stroke="currentColor"
              stroke-width="4"
            ></circle>
            <path
              class="opacity-75"
              fill="currentColor"
              d="M4 12a8 8 0 018-8v4a4 4 0 00-4 4H4z"
            ></path>
          </svg>
        </div>
      </div>
    </section>
  `,
})
export class CreditosSearchComponent {
  private readonly service = inject(CreditoService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  form = new FormGroup({
    numeroNfse: new FormControl<string | null>(null),
    numeroCredito: new FormControl<string | null>(null),
  });

  creditos = signal<Credito[]>([]);
  loading = signal(false);
  error = signal<string | null>(null);

  private normalize(v: string | null | undefined) {
    return v ? v.trim() : '';
  }

  async onSubmit() {
    this.error.set(null);

    const nfse = this.normalize(this.form.value.numeroNfse);
    const numeroCredito = this.normalize(this.form.value.numeroCredito);

    if ((nfse && numeroCredito) || (!nfse && !numeroCredito)) {
      this.error.set('Preencha apenas um dos campos.');
      return;
    }

    try {
      this.loading.set(true);

      if (nfse) {
        const res = await firstValueFrom(this.service.findByNumeroNfse(nfse));
        this.creditos.set(res ?? []);
      } else {
        const res = await firstValueFrom(
          this.service.findByNumeroCredito(numeroCredito)
        );
        this.creditos.set(res ? [res] : []);
      }
    } catch {
      this.error.set('Erro ao buscar dados.');
      this.creditos.set([]);
    } finally {
      this.loading.set(false);
    }
  }

  clear() {
    this.form.reset();
    this.creditos.set([]);
    this.error.set(null);
  }
}
