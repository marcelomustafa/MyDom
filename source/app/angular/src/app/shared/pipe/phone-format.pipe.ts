import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'phoneFormat'
})
export class PhoneFormatPipe implements PipeTransform {
  transform(value: string): string {
    if (!value) {
      return '';
    }

    // Remove todos os caracteres não numéricos
    const cleanedNumber = value.replace(/\D/g, '');

    // Verifica se é um número de celular (11 dígitos) ou telefone fixo (10 dígitos)
    if (cleanedNumber.length === 11) {
      return cleanedNumber.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
    } else if (cleanedNumber.length === 10) {
      return cleanedNumber.replace(/(\d{2})(\d{4})(\d{4})/, '($1) $2-$3');
    } else {
      return value;
    }
  }
}