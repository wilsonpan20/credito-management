import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'creditos' },
  {
    path: 'creditos/nfse/:numeroNfse',
    loadComponent: () =>
      import('./creditos/creditos-search.component').then(m => m.CreditosSearchComponent)
  },
  {
    path: 'creditos/:numeroCredito',
    loadComponent: () =>
      import('./creditos/credito-detail.component').then(m => m.CreditoDetailComponent)
  },
  {
    path: 'creditos',
    pathMatch: 'full',
    loadComponent: () =>
      import('./creditos/creditos-search.component').then(m => m.CreditosSearchComponent)
  },
  { path: '**', redirectTo: 'creditos' }
];
