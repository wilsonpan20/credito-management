import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { of } from 'rxjs';
import { vi } from 'vitest';
import { ActivatedRoute, Router } from '@angular/router';
import { CreditosSearchComponent } from './creditos-search.component';
import { CreditoService } from '../services/credito.service';

describe('CreditosSearchComponent', () => {
  let component: CreditosSearchComponent;
  let fixture: ComponentFixture<CreditosSearchComponent>;
  let serviceSpy: { findByNumeroNfse: ReturnType<typeof vi.fn>; findByNumeroCredito: ReturnType<typeof vi.fn> };

  beforeEach(async () => {
    serviceSpy = { findByNumeroNfse: vi.fn(), findByNumeroCredito: vi.fn() } as any;

    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, CreditosSearchComponent],
      providers: [
        { provide: CreditoService, useValue: serviceSpy },
        { provide: ActivatedRoute, useValue: { paramMap: of({ get: (_: string) => null }) } },
        { provide: Router, useValue: { navigate: vi.fn() } },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(CreditosSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should set an error when both fields are filled', async () => {
    component.form.setValue({ numeroNfse: '123', numeroCredito: '100' });

    await component.onSubmit();

    expect(component.error()).toBe('Preencha apenas um dos campos.');
  });

  it('should call findByNumeroNfse when nfse is provided', async () => {
    const fake = [{ numeroCredito: '1' } as any];
    (serviceSpy.findByNumeroNfse as any).mockReturnValue(of(fake));

    component.form.setValue({ numeroNfse: '123', numeroCredito: '' });
    await component.onSubmit();

    expect(serviceSpy.findByNumeroNfse).toHaveBeenCalledWith('123');
    expect(component.creditos()).toEqual(fake);
  });

  it('should call findByNumeroCredito when numeroCredito is provided', async () => {
    const fake = { numeroCredito: '100' } as any;
    (serviceSpy.findByNumeroCredito as any).mockReturnValue(of(fake));

    component.form.setValue({ numeroNfse: '', numeroCredito: '100' });
    await component.onSubmit();

    expect(serviceSpy.findByNumeroCredito).toHaveBeenCalledWith('100');
    expect(component.creditos()).toEqual([fake]);
  });
});