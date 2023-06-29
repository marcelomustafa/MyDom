import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'utcDate'
})
export class UtcDatePipe implements PipeTransform {
  transform(value: string | Date): string {
    if (!value) {
      return '';
    }

    const date = new Date(value);
    const localDate = new Date(date.getTime() + date.getTimezoneOffset() * 60000);

    return localDate.toLocaleDateString();
  }
}