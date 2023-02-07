import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppRootModule } from 'src/app/page/main/root/app-root.module';

platformBrowserDynamic().bootstrapModule(AppRootModule)
  .catch(err => console.error(err));
