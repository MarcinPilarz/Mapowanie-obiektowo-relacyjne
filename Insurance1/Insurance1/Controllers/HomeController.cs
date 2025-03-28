using Microsoft.AspNetCore.Mvc;

namespace Insurance1.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }
    }
}
