import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { PlayLabSeqComponent } from './playLabSeq/playLabSeq.component';

export const routes: Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'home', component:HomeComponent},
    {path: 'playLabSeq', component:PlayLabSeqComponent},
];
