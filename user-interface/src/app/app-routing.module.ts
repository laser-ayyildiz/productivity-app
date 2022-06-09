import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HabitComponent } from './habit/habit.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { route_paths } from './_helpers/route_paths';
import { TodoComponent } from './todo/todo.component';
import { AuthGuard } from './_services/auth.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: route_paths.DASHBOARD,
    pathMatch: 'full'
  },
  {
    path: route_paths.LOGIN,
    component: LoginComponent
  },
  {
    path: route_paths.REGISTER,
    component: RegisterComponent
  },
  {
    path: route_paths.TODO,
    canActivate: [AuthGuard],
    component: TodoComponent
  },
  {
    path: route_paths.DASHBOARD,
    canActivate: [AuthGuard],
    component: DashboardComponent
  },
  {
    path: route_paths.HABIT,
    canActivate: [AuthGuard],
    component: HabitComponent
  },
  {
    path: route_paths.PROFILE,
    canActivate: [AuthGuard],
    component: ProfileComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
