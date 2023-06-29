import { Pipe, PipeTransform } from '@angular/core';


/*EXEMPLO: <p>{{ price | currency: 'USD' | number: '1.2-2' }}</p>*/
@Pipe({
  name: 'decimalFormat'
})
export class DecimalFormatPipe implements PipeTransform {
  transform(value: number, decimalPlaces: number = 2, currencySymbol: string = ''): string {
    if (isNaN(value) || value === null) {
      return '';
    }

    const formattedValue = value.toFixed(decimalPlaces);
    return currencySymbol ? `${currencySymbol} ${formattedValue}` : formattedValue;
  }
}
