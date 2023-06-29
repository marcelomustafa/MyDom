import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'currencyDecimalV2' })
export class CurrencyDecimalV2Pipe implements PipeTransform {
  transform(value: number, currencyCode: string): string {
    // Formata o valor como moeda
    let formattedValue = new Intl.NumberFormat('en-US', { style: 'currency', currency: currencyCode }).format(value);

    // Limita o número de casas decimais
    const decimalSeparator = formattedValue.indexOf('.') !== -1 ? '.' : ',';
    const decimalPart = formattedValue.split(decimalSeparator)[1];
    const decimalDigits = decimalPart ? decimalPart.length : 0;
    const maxDecimalDigits = 2; // Máximo de duas casas decimais
    if (decimalDigits > maxDecimalDigits) {
      formattedValue = formattedValue.slice(0, formattedValue.length - (decimalDigits - maxDecimalDigits));
    }

    return formattedValue;
  }
}
