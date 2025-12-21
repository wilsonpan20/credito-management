export interface Credito {
  id?: number;
  numeroCredito: string;
  numeroNfse: string;
  dataConstituicao: string; // ISO date string (YYYY-MM-DD)
  valorIssqn: number;
  tipoCredito: string;
  simplesNacional: boolean;
  aliquota: number;
  valorFaturado: number;
  valorDeducao: number;
  baseCalculo: number;
}
