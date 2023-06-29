import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'currencyDecimal'
})
export class CurrencyDecimalPipe implements PipeTransform {
  transform(value: number, currencyCode: string): string {
    // Verifica se o valor é válido
    if (isNaN(value) || value === null) {
      return '';
    }

    // Formata o valor como moeda
    const formattedValue = new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: currencyCode,
      minimumFractionDigits: 0,
      maximumFractionDigits: 2
    }).format(value);

    return formattedValue;
  }
}
